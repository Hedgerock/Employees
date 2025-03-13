package com.hedgerock.spring.mvc_hibernate_aop.utils;

public class QueryTemplates {
    public static final String TEMPLATE_OF_GET_ALL_REQUEST = "FROM %s";
    public static final String TEMPLATE_OF_INSERTING_QUERY_PARAMS = "%s = %s";
    public static final String TEMPLATE_OF_ROOT_QUERY = "FROM %s WHERE " + TEMPLATE_OF_INSERTING_QUERY_PARAMS;
    public static final String TEMPLATE_OF_JOINING_QUERY = " AND ";
    public static final String QUERY_WITH_SIBLINGS = "FROM %s e WHERE ";
    public static final String QUERY_WITH_PLACE = "e.%s = :currentId";
    public static final String QUERY_FOR_MAX_ID = "SELECT MAX(id) FROM %s WHERE id < :id";
    public static final String QUERY_FOR_MIN_ID = "SELECT MIN(id) FROM %s WHERE id > :id";
    public static final String QUERY_WITHOUT_PLACE =
            "e.id =" + "(" + QUERY_FOR_MAX_ID + ") " +
            "OR e.id =" + "(" + QUERY_FOR_MIN_ID + ") " +
            "OR e.id = :id";

    public static final String WITH_FIRE_DATE = "fireDate IS NOT NULL";
    public static final String WITHOUT_FIRE_DATE = "fireDate IS NULL";

    public static final String QUERY_WITHOUT_PLACE_AND_NOT_FIRED =
            "e.id =" + "(" + QUERY_FOR_MAX_ID + " AND " + WITHOUT_FIRE_DATE + ") " +
            "OR e.id =" + "(" + QUERY_FOR_MIN_ID + " AND "  + WITHOUT_FIRE_DATE + ") " +
            "OR e.id = :id AND " + WITHOUT_FIRE_DATE;

    public static final String QUERY_WITHOUT_PLACE_AND_FIRED =
            "e.id =" + "(" + QUERY_FOR_MAX_ID + " AND " + WITH_FIRE_DATE + ") " +
            "OR e.id =" + "(" + QUERY_FOR_MIN_ID + " AND "  + WITH_FIRE_DATE + ") " +
            "OR e.id = :id AND " + WITH_FIRE_DATE;

    public static final String QUERY_WITH_PLACE_AND_ID =
            "e.id =" + "(" + QUERY_FOR_MAX_ID + " AND " + "%s = :currentId) " +
            "OR e.id =" + "(" + QUERY_FOR_MIN_ID + " AND " + "%s = :currentId) " +
            "OR e.id = :id";
    public static final String QUERY_WITH_PLACE_AND_ID_AND_NOT_FIRED =
            "e.id =" + "(" + QUERY_FOR_MAX_ID + " AND " + "%s = :currentId AND " + WITHOUT_FIRE_DATE + ") " +
            "OR e.id =" + "(" + QUERY_FOR_MIN_ID + " AND " + "%s = :currentId AND " + WITHOUT_FIRE_DATE + ") " +
            "OR e.id = :id AND " + WITHOUT_FIRE_DATE;

    public static final int CURRENT_INDEX_FOR_SALARY = 4;

    public static final String JOINS = "LEFT JOIN FETCH e.department " +
            "LEFT JOIN FETCH e.nationality " +
            "LEFT JOIN FETCH e.city " +
            "LEFT JOIN FETCH e.employeeDetails ed " +
            "LEFT JOIN FETCH ed.emails " +
            "LEFT JOIN FETCH ed.phoneNumbers " +
            "LEFT JOIN FETCH ed.socialMedia " +
            "LEFT JOIN FETCH ed.picture " +
            "LEFT JOIN FETCH ed.employeeDescription ";

    private QueryTemplates() {}
}
