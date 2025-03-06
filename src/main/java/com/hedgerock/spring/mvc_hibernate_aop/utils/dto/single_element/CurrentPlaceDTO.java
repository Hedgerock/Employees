package com.hedgerock.spring.mvc_hibernate_aop.utils.dto.single_element;

import java.time.Duration;

public class CurrentPlaceDTO {
    private Long id;
    private String name;
    private int minSalary;
    private int maxSalary;
    private int totalEmployees;
    private String previousChanges;
    private String previousShortChanges;
    private Duration timeStampDiff;

    public CurrentPlaceDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(int minSalary) {
        this.minSalary = minSalary;
    }

    public int getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(int maxSalary) {
        this.maxSalary = maxSalary;
    }

    public int getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(int totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public String getPreviousChanges() {
        return previousChanges;
    }

    public void setPreviousChanges(String previousChanges) {
        this.previousChanges = previousChanges;
    }

    public String getPreviousShortChanges() {
        return previousShortChanges;
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
