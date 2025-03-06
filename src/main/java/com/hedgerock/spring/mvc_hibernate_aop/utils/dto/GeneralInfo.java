package com.hedgerock.spring.mvc_hibernate_aop.utils.dto;

public class GeneralInfo {
    private Long totalEmployees;
    private Long totalDepartments;
    private Long totalCities;
    private Long totalNationalities;
    private Long totalSalary;
    private String totalSalaryModified;

    public GeneralInfo() {
    }

    public Long getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(Long totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public Long getTotalDepartments() {
        return totalDepartments;
    }

    public void setTotalDepartments(Long totalDepartments) {
        this.totalDepartments = totalDepartments;
    }

    public Long getTotalCities() {
        return totalCities;
    }

    public void setTotalCities(Long totalCities) {
        this.totalCities = totalCities;
    }

    public String getTotalSalaryModified() {
        String string = this.totalSalary.toString();
        StringBuilder stringBuilder = new StringBuilder(string);

        for (int i = stringBuilder.length() - 3; i > 0; i-= 3) {
            stringBuilder.insert(i, "_");
        }

        return stringBuilder.toString();
    }

    public void setTotalSalaryModified(String totalSalaryModified) {
        this.totalSalaryModified = totalSalaryModified;
    }

    public Long getTotalSalary() {
        return totalSalary;
    }

    public Long getTotalNationalities() {
        return totalNationalities;
    }

    public void setTotalNationalities(Long totalNationalities) {
        this.totalNationalities = totalNationalities;
    }

    public void setTotalSalary(Long totalSalary) {
        this.totalSalary = totalSalary;
    }
}
