package com.hedgerock.spring.mvc_hibernate_aop.utils.enums;

public enum QueryParams {
    FIRST_NAME("firstName"),
    LAST_NAME("lastName"),
    MIDDLE_NAME("middleName"),
    DEPARTMENT("department.name"),
    SALARY("salary"),
    CITY("city.name"),
    NATIONALITY("nationality.name");

    private final String name;

    QueryParams(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
