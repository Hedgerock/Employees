package com.hedgerock.spring.mvc_hibernate_aop.utils.dto.single_element;

import java.time.Duration;

public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private Long departmentId;
    private String departmentName;
    private String previousChanges;
    private String previousShortChanges;
    private Duration timeStampDiff;

    public EmployeeDTO() {
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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPreviousChanges() {
        return this.previousChanges;
    }

    public void setPreviousChanges(String previousChanges) {
        this.previousChanges = previousChanges;
    }

    public String getPreviousShortChanges() {
        return this.previousShortChanges;
    }

    public void setPreviousShortChanges(String previousShortChanges) {
        this.previousShortChanges = previousShortChanges;
    }

    public Duration getTimeStampDiff() {
        return timeStampDiff;
    }

    public void setTimeStampDiff(Duration timeStampDiff) {
        this.timeStampDiff = timeStampDiff;
    }
}
