package com.hedgerock.spring.mvc_hibernate_aop.utils.dto.revisions;

public class CurrentPlaceRevisionDTO {
    private Long id;
    private Long rev;
    private int revType;
    private String name;
    private int minSalary;
    private int maxSalary;
    private int totalEmployees;
    private String revisionTimestamp;
    private String updatedBy = "Hedgerock";

    private String oldName;
    private int oldMinSalary;
    private int oldMaxSalary;
    private int oldTotalEmployees;

    public CurrentPlaceRevisionDTO() {}

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

    public String getRevisionTimestamp() {
        return revisionTimestamp;
    }

    public void setRevisionTimestamp(String revisionTimestamp) {
        this.revisionTimestamp = revisionTimestamp;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public int getOldMinSalary() {
        return oldMinSalary;
    }

    public void setOldMinSalary(int oldMinSalary) {
        this.oldMinSalary = oldMinSalary;
    }

    public int getOldMaxSalary() {
        return oldMaxSalary;
    }

    public void setOldMaxSalary(int oldMaxSalary) {
        this.oldMaxSalary = oldMaxSalary;
    }

    public int getOldTotalEmployees() {
        return oldTotalEmployees;
    }

    public void setOldTotalEmployees(int oldTotalEmployees) {
        this.oldTotalEmployees = oldTotalEmployees;
    }
}
