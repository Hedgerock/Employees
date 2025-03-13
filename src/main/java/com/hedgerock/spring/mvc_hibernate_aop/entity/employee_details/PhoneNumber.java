package com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details;

import jakarta.persistence.*;


@Entity
@Table(name = "employee_phone_numbers")
public class PhoneNumber {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private String value;

    @Column(name = "employee_details_id", insertable = false, updatable = false)
    private Long employeeDetailsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_details_id")
    EmployeeDetails employeeDetails;

    public PhoneNumber() {
    }

    public PhoneNumber(String value, EmployeeDetails employeeDetails) {
        this.value = value;
        this.employeeDetails = employeeDetails;
    }

    public PhoneNumber(String value) {
        this.value = value;
    }


    public EmployeeDetails getEmployeeDetails() {
        return employeeDetails;
    }

    public void setEmployeeDetails(EmployeeDetails employeeDetails) {
        this.employeeDetails = employeeDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getEmployeeDetailsId() {
        return employeeDetailsId;
    }

    public void setEmployeeDetailsId(Long employeeDetailsId) {
        this.employeeDetailsId = employeeDetailsId;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
