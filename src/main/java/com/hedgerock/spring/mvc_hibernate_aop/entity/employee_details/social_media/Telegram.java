package com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.social_media;

import jakarta.persistence.*;

@Entity
@Table(name="telegram")
public class Telegram {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "link")
    private String link;

    public Telegram() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}