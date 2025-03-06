package com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="pictures")
public class Picture {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="link")
    private String pictureSrc;

    @OneToMany(mappedBy = "picture", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PrevPicture> pictures;

    @OneToOne(mappedBy = "picture", cascade = CascadeType.ALL)
    EmployeeDetails employeeDetails;

    public Picture() {
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

    public void addPicture(PrevPicture picture) {
        if (this.pictures == null) {
            this.pictures = new ArrayList<>();
        }

        this.pictures.add(picture);
        picture.setPicture(this);
    }

    public List<PrevPicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<PrevPicture> pictures) {
        this.pictures = pictures;
    }

    public void setPictureSrc(String pictureSrc) {
        this.pictureSrc = pictureSrc;
    }

    public EmployeeDetails getEmployeeDetails() {
        return employeeDetails;
    }

    public void setEmployeeDetails(EmployeeDetails employeeDetails) {
        this.employeeDetails = employeeDetails;
    }

}
