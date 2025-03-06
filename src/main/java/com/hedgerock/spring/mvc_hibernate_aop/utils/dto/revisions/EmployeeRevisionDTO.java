package com.hedgerock.spring.mvc_hibernate_aop.utils.dto.revisions;

import java.time.LocalDate;

public class EmployeeRevisionDTO {
    private Long id;
    private Long rev;
    private int revType;
    private Long departmentId;
    private Long employeeDetailsId;
    private String firstName;
    private LocalDate hireDate;
    private String lastName;
    private String middleName;
    private int salary;
    private String revisionTimestamp;
    private String departmentName;
    private String updatedBy = "Hedgerock";

    private String oldFirstName;
    private String oldLastName;
    private String oldMiddleName;
    private Long oldDepartmentId;
    private LocalDate oldHireDate;
    private int oldSalary;
    private String oldDepartmentName;

    public EmployeeRevisionDTO () {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRev() {
        return rev;
    }

    public void setRev(Long rev) {
        this.rev = rev;
    }

    public int getRevType() {
        return revType;
    }

    public void setRevType(int revType) {
        this.revType = revType;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getEmployeeDetailsId() {
        return employeeDetailsId;
    }

    public void setEmployeeDetailsId(Long employeeDetailsId) {
        this.employeeDetailsId = employeeDetailsId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public String getRevisionTimestamp() {
        return revisionTimestamp;
    }

    public void setRevisionTimestamp(String revisionTimestamp) {
        this.revisionTimestamp = revisionTimestamp;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getOldFirstName() {
        return oldFirstName;
    }

    public void setOldFirstName(String oldFirstName) {
        this.oldFirstName = oldFirstName;
    }

    public String getOldLastName() {
        return oldLastName;
    }

    public void setOldLastName(String oldLastName) {
        this.oldLastName = oldLastName;
    }

    public String getOldMiddleName() {
        return oldMiddleName;
    }

    public void setOldMiddleName(String oldMiddleName) {
        this.oldMiddleName = oldMiddleName;
    }

    public Long getOldDepartmentId() {
        return oldDepartmentId;
    }

    public void setOldDepartmentId(Long oldDepartmentId) {
        this.oldDepartmentId = oldDepartmentId;
    }

    public LocalDate getOldHireDate() {
        return oldHireDate;
    }

    public void setOldHireDate(LocalDate oldHireDate) {
        this.oldHireDate = oldHireDate;
    }

    public int getOldSalary() {
        return oldSalary;
    }

    public void setOldSalary(int oldSalary) {
        this.oldSalary = oldSalary;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getOldDepartmentName() {
        return oldDepartmentName;
    }

    public void setOldDepartmentName(String oldDepartmentName) {
        this.oldDepartmentName = oldDepartmentName;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
