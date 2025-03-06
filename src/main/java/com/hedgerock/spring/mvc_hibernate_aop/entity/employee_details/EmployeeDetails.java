package com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details;

import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.social_media.EmployeeDescription;
import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.social_media.SocialMedia;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.id_collectors.IdFinder;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee_details")
public class EmployeeDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @OneToOne(
            mappedBy = "employeeDetails",
            fetch = FetchType.EAGER
    )
    private Employee employee;

    @OneToMany(
            mappedBy = "employeeDetails",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<Email> emails;

    @OneToMany(
            mappedBy = "employeeDetails",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<PhoneNumber> phoneNumbers;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="picture_id")
    private Picture picture;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="social_media_id")
    private SocialMedia socialMedia;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="employee_description_id")
    private EmployeeDescription employeeDescription;

    @Column(name="picture_id", insertable = false, updatable = false)
    private Long pictureId;

    @Column(name="social_media_id", insertable = false, updatable = false)
    private Long socialMediaId;

    @Column(name="employee_description_id", insertable = false, updatable = false)
    private Long employeeDescriptionId;

    @Transient
    private Long potentialParentId;

    @Transient boolean isEditForCurrentDepartment;

    @Transient
    private List<String>emailsValue;

    @Transient
    private List<String> phonesValue;

    @Transient
    private List<Long>emailIdList;

    @Transient
    private List<String> phoneIdList;

    @Transient
    private Boolean hasNullFields;

    @Transient
    private IdFinder idFinder = new IdFinder();

    public EmployeeDetails() {
        this.socialMedia = new SocialMedia();
        this.employeeDescription = new EmployeeDescription();
    }

    public void addEmails(List<Email> emails) {
        if (this.emails == null) {
            this.emails = new ArrayList<>();
        }

       this.emails.clear();
       emails.forEach(email -> {
            this.emails.add(email);
            email.setEmployeeDetails(this);
       });
    }

    public void addPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        if (this.phoneNumbers == null) {
            this.phoneNumbers = new ArrayList<>();
        }

        phoneNumbers.forEach(phoneNumber -> {
            this.phoneNumbers.add(phoneNumber);
            phoneNumber.setEmployeeDetails(this);
        });
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPotentialParentId() {
        return potentialParentId;
    }

    public List<Long> getEmailIdList() {
        return emailIdList;
    }

    public void setEmailIdList(List<Long> emailIdList) {
        this.emailIdList = emailIdList;
    }

    public List<String> getPhoneIdList() {
        return phoneIdList;
    }

    public void setPhoneIdList(List<String> phoneIdList) {
        this.phoneIdList = phoneIdList;
    }

    public void setPotentialParentId(Long potentialParentId) {
        this.potentialParentId = potentialParentId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Long getPictureId() {
        return pictureId;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<String> getEmailsValue() {
        return emailsValue;
    }

    public void setEmailsValue(List<String> emailsValue) {
        this.emailsValue = emailsValue;
    }

    public List<String> getPhonesValue() {
        return phonesValue;
    }

    public void setPhonesValue(List<String> phonesValue) {
        this.phonesValue = phonesValue;
    }

    public boolean getIsEditForCurrentDepartment() {
        return isEditForCurrentDepartment;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public Long getSocialMediaId() {
        return socialMediaId;
    }

    public SocialMedia getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(SocialMedia socialMedia) {
        this.socialMedia = socialMedia;
    }

    public void setSocialMediaId(Long socialMediaId) {
        this.socialMediaId = socialMediaId;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public IdFinder getIdFinder() {
        return idFinder;
    }

    public void setIdFinder(IdFinder idFinder) {
        this.idFinder = idFinder;
    }

    public Boolean getHasNullFields() {
        return hasNullFields;
    }

    public void setHasNullFields(Boolean hasNullFields) {
        this.hasNullFields = hasNullFields;
    }

    public Long getEmployeeDescriptionId() {
        return employeeDescriptionId;
    }

    public void setEmployeeDescriptionId(Long employeeDescriptionId) {
        this.employeeDescriptionId = employeeDescriptionId;
    }

    public EmployeeDescription getEmployeeDescription() {
        return employeeDescription;
    }

    public void setEmployeeDescription(EmployeeDescription employeeDescription) {
        this.employeeDescription = employeeDescription;
    }

    @Override
    public String toString() {
        return "EmployeeDetails{" +
                "id=" + id +
                '}';
    }
}
