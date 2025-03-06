package com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details;

import jakarta.persistence.*;

@Entity
@Table(name = "emails")
public class Email {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private String value;

    @Column(name = "employee_details_id", insertable = false, updatable = false)
    private Long employeeDetailsId;

    @ManyToOne
    @JoinColumn(name = "employee_details_id")
    private EmployeeDetails employeeDetails;


    public Email() {
    }

    public Email(String value, EmployeeDetails employeeDetails) {
        this.value = value;
        this.employeeDetails = employeeDetails;
    }

    public Email(String value) {
        this.value = value;
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

    public EmployeeDetails getEmployeeDetails() {
        return employeeDetails;
    }

    public Long getEmployeeDetailsId() {
        return employeeDetailsId;
    }

    public void setEmployeeDetailsId(Long employeeDetailsId) {
        this.employeeDetailsId = employeeDetailsId;
    }

    public void setEmployeeDetails(EmployeeDetails employeeDetails) {
        this.employeeDetails = employeeDetails;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
