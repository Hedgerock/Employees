package com.hedgerock.spring.mvc_hibernate_aop.utils.dto;

import java.time.Duration;

public class TimeCarrier {

    private Long difference;
    private String dateDifference;
    private String hireDate;
    private String fireDate;
    private String previousChanges;
    private Duration timeStampDiff;

    public TimeCarrier() {
    }

    public Long getDifference() {
        return difference;
    }

    public void setDifference(Long difference) {
        this.difference = difference;
    }

    public String getDateDifference() {
        return dateDifference;
    }

    public void setDateDifference(String dateDifference) {
        this.dateDifference = dateDifference;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getPreviousChanges() {
        return previousChanges;
    }

    public void setPreviousChanges(String previousChanges) {
        this.previousChanges = previousChanges;
    }

    public Duration getTimeStampDiff() {
        return timeStampDiff;
    }

    public void setTimeStampDiff(Duration timeStampDiff) {
        this.timeStampDiff = timeStampDiff;
    }

    public String getFireDate() {
        return fireDate;
    }

    public void setFireDate(String fireDate) {
        this.fireDate = fireDate;
    }
}
