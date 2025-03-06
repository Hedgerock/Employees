package com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details;

import jakarta.persistence.*;

@Entity
@Table(name="prev_pictures")
public class PrevPicture {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "link")
    private String pictureSrc;

    @ManyToOne
    @JoinColumn(name = "picture_id")
    Picture picture;

    public PrevPicture() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPictureSrc() {
        return pictureSrc;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public void setPictureSrc(String pictureSrc) {
        this.pictureSrc = pictureSrc;
    }
}
