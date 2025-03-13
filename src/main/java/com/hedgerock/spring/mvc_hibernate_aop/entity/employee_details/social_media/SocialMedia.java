package com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.social_media;

import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.id_collectors.IdFinder;
import jakarta.persistence.*;

@Entity
@Table(name = "social_media")
public class SocialMedia {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "viber_id", insertable = false, updatable = false)
    private Long viberId;

    @Column(name = "whatsapp_id", insertable = false, updatable = false)
    private Long whatsAppId;

    @Column(name = "telegram_id", insertable = false, updatable = false)
    private Long telegramId;

    @Column(name = "linkedin_id", insertable = false, updatable = false)
    private Long linkedInId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "viber_id")
    private Viber viber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "telegram_id")
    private Telegram telegram;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "whatsapp_id")
    private WhatsApp whatsApp;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "linkedin_id")
    private LinkedIn linkedIn;

    @Transient
    private IdFinder idFinder = new IdFinder();

    public SocialMedia() {
        this.viber = new Viber();
        this.telegram = new Telegram();
        this.whatsApp = new WhatsApp();
        this.linkedIn = new LinkedIn();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getViberId() {
        return viberId;
    }

    public void setViberId(Long viberId) {
        this.viberId = viberId;
    }

    public Long getWhatsAppId() {
        return whatsAppId;
    }

    public IdFinder getIdFinder() {
        return idFinder;
    }

    public void setIdFinder(IdFinder idFinder) {
        this.idFinder = idFinder;
    }

    public void setWhatsAppId(Long whatsAppId) {
        this.whatsAppId = whatsAppId;
    }

    public Long getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(Long telegramId) {
        this.telegramId = telegramId;
    }

    public Viber getViber() {
        return viber;
    }

    public void setViber(Viber viber) {
        this.viber = viber;
    }

    public Telegram getTelegram() {
        return telegram;
    }

    public void setTelegram(Telegram telegram) {
        this.telegram = telegram;
    }

    public WhatsApp getWhatsApp() {
        return whatsApp;
    }

    public void setWhatsApp(WhatsApp whatsApp) {
        this.whatsApp = whatsApp;
    }

    public LinkedIn getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(LinkedIn linkedIn) {
        this.linkedIn = linkedIn;
    }

    public Long getLinkedInId() {
        return linkedInId;
    }

    public void setLinkedInId(Long linkedInId) {
        this.linkedInId = linkedInId;
    }
}
