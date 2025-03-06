package com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.social_media;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_description")
public class EmployeeDescription {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private String value;


    public EmployeeDescription() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
