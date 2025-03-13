package com.hedgerock.spring.mvc_hibernate_aop.entity;

import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.user_dtos.UserDetailsDTO;
import com.hedgerock.spring.mvc_hibernate_aop.utils.validation.CheckEmail;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

@Entity
@Table(name="users_details")
public class UserDetails {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @NotEmpty
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty
    private String lastName;

    @Column(name = "middle_name")
    @NotEmpty
    private String middleName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "email")
    @NotEmpty
    @CheckEmail
    private String email;

    @Column(name="phone_number")
    @NotEmpty
    private String phoneNumber;


    public UserDetails() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void initDtoContent(UserDetailsDTO userDetailsDTO) {
        this.firstName = userDetailsDTO.getFirstName();
        this.lastName = userDetailsDTO.getLastName();
        this.middleName = userDetailsDTO.getMiddleName();
        this.dateOfBirth = userDetailsDTO.getDateOfBirth();
        this.email = userDetailsDTO.getEmail();
        this.phoneNumber = userDetailsDTO.getPhoneNumber();
    }
}
