package com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.social_media;

import com.hedgerock.spring.mvc_hibernate_aop.utils.validation.DefaultValidationParams;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="viber")
public class Viber {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "link")
    @Pattern(regexp = DefaultValidationParams.PHONE_VALIDATION, message = "Wrong pattern")
    private String link;

    public Viber() {
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
