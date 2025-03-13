package com.hedgerock.spring.mvc_hibernate_aop.utils;

import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.EmployeeDetails;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.City;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Department;
import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Nationality;
import com.hedgerock.spring.mvc_hibernate_aop.utils.enums.QueryParams;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.QueryTemplates.*;

public class DAOUtil {
    public static String getSearchParams(String search) {

        String[] searchParams = search.split(" ");
        int length = searchParams.length;
        QueryParams[] queryParams = QueryParams.values();

        if (length > queryParams.length) {
            return "";
        }

        StringBuilder query = new StringBuilder("SELECT e FROM Employee e " +
                "JOIN FETCH e.department " +
                "JOIN FETCH e.nationality " +
                "JOIN FETCH e.city " +
                "JOIN FETCH e.employeeDetails ed " +
                "LEFT JOIN FETCH ed.emails " +
                "LEFT JOIN FETCH ed.phoneNumbers " +
                "LEFT JOIN FETCH ed.socialMedia " +
                "LEFT JOIN FETCH ed.picture " +
                "LEFT JOIN FETCH ed.employeeDescription " +
                "WHERE ");

        IntStream.range(0, length)
                .forEach(i -> {
                    String currentTitle = queryParams[i].getName();
                    query.append("e.").append(currentTitle).append(" = :param").append(i);

                    if (i < length - 1) {
                        query.append(TEMPLATE_OF_JOINING_QUERY);
                    }

                });

        return query.toString();
    }

    public static String validateQuery(String currentValue, int i) {
        if (i == CURRENT_INDEX_FOR_SALARY && !currentValue.matches("^\\d+$")) {
            return "0";
        }

        System.out.println();

        return currentValue;
    }

    @SafeVarargs
    public static <T> String getEntityName(String template, T... arguments) {
        return String.format(template, (Object[]) arguments);
    }

    public static <T> Query<T> initQuery(Class<T>tClass, Session session, String entityName) {
        return session.createQuery(entityName, tClass);
    }

    public static Query<Employee> getReadyQuery(String search, Session session, String entityName, Long id) {
        String[] searchParams = search.split(" ");

        Query<Employee> employeeQuery = initQuery(Employee.class, session, entityName);

        IntStream.range(0, searchParams.length).forEach(i -> {
            employeeQuery.setParameter("param" + i, DAOUtil.validateQuery(searchParams[i], i) );
        });

        employeeQuery.setParameter("currentId", id);

        return employeeQuery;
    }

    public static Query<Employee> getReadyQuery(String search, Session session, String entityName) {
        String[] searchParams = search.split(" ");

        Query<Employee> employeeQuery = initQuery(Employee.class, session, entityName);

        IntStream.range(0, searchParams.length).forEach(i -> {
            employeeQuery.setParameter("param" + i, DAOUtil.validateQuery(searchParams[i], i) );
        });

        return employeeQuery;
    }

    public static <T> void updateStatistics(Session session, Long currentId, String queryParam, T entity) {
        String hql = "SELECT COALESCE(%s(e.salary), 0) FROM Employee e WHERE e." + queryParam + " = :currentId" +
                " AND e.fireDate IS NULL";

        Integer minSalary = session.createQuery(String.format(hql, "MIN"), Integer.class)
                .setParameter("currentId", currentId)
                .uniqueResult();
        callSetter(entity, "setMinSalary", minSalary);

        Integer maxSalary = session.createQuery(String.format(hql, "MAX"), Integer.class)
                .setParameter("currentId", currentId)
                .uniqueResult();
        callSetter(entity, "setMaxSalary", maxSalary);

        Long totalEmployees = session.createQuery(String.format(hql, "COUNT"), Long.class)
                .setParameter("currentId", currentId)
                .uniqueResult();
        callSetter(entity, "setTotalEmployees", totalEmployees.intValue());

        session.merge(entity);
    }


    private static <T> void callSetter(T entity, String setterName, Object value) {
        try {
            Method setter = entity.getClass().getMethod(setterName, value.getClass());
            setter.invoke(entity, value);
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong " + e.getMessage());
        }
    }

    public static <T> T getValue(Class<T> currentEntityClass, Long id, Session session) {
        return id != null ? session.get(currentEntityClass, id) : null;
    }

    public static Employee saveSingleEmployee(Employee employee, Long depId, Long cityId, Long natId, Session session) {
        Department oldDepartment = DAOUtil.getValue(Department.class, depId, session);
        City oldCity = DAOUtil.getValue(City.class, cityId, session);
        Nationality oldNationality = DAOUtil.getValue(Nationality.class, natId, session);


        EmployeeDetails newEmpDetails = employee.getEmployeeDetails();

        // checkIfEmployeeDetailsExists
        if (newEmpDetails != null && employee.getEmployeeDetailsId() != null) {
            EmployeeDetails existingDetails = session.find(EmployeeDetails.class, employee.getEmployeeDetailsId());
            employee.setEmployeeDetails(Objects.requireNonNullElse(existingDetails, newEmpDetails));
        }

        Employee currentEmployee = session.merge(employee);

        Long newDepId = getActualId(currentEmployee.getDepartment());
        Long newCityId = getActualId(currentEmployee.getCity());
        Long newNatId = getActualId(currentEmployee.getNationality());

        if (newDepId != null) {
            DAOUtil.updateValue(
                    Department.class, "department.id", newDepId, depId, session, oldDepartment);
        }
        if (newCityId != null) {
            DAOUtil.updateValue(
                    City.class, "city.id", newCityId, cityId, session, oldCity);
        }

        if (newNatId != null) {
            DAOUtil.updateValue(
                    Nationality.class, "nationality.id", newNatId, natId, session, oldNationality);
        }

        return currentEmployee;
    }

    private static <T> Long getActualId(T entity) {
        try {
            if (entity != null) {
                Method method = entity.getClass().getMethod("getId");
                return (Long) method.invoke(entity);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException("No such method found");
        }
    }


    public static <T> void updateValue(
            Class<T>currentEntityClass, String tableFieldValue, Long curId, Long prevId, Session session, T prevEntity) {
        T currentEntity = session.get(currentEntityClass, curId);

        if (prevId != null && prevEntity != null) {
            updateStatistics(session, prevId, tableFieldValue, prevEntity);
        }

        if (curId != null && currentEntity != null) {
            updateStatistics(session, curId, tableFieldValue, currentEntity);
        }

    }


    public static String getCurrentDateValue(Duration duration) {
        long totalDays = duration.toDays();
        long years = totalDays / 365;
        long remainingDaysAfterYears = totalDays % 365;
        long months = remainingDaysAfterYears / 30;
        long days = remainingDaysAfterYears % 30;

        long hours = duration.toHours() % 24;
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;

        return String.format("%02d:%02d:%02d:%02d:%02d:%02d", years, months, days, hours, minutes, seconds);
    }

    public static <T> void initTruncation(T entity, String setEntity, String setId, Class<T>tClass) {
        try {
            Method method = entity.getClass().getDeclaredMethod("getEmployee");
            List<?> employees = (List<?>) method.invoke(entity);

            if (employees != null) {
                employees.forEach(el -> truncateEntity((Employee) el, setEntity, setId, tClass));
            }

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static <T> void truncateEntity(Employee value, String setEntity, String setId, Class<T>tClass) {
        try {
            Method setEntityMethod = value.getClass().getDeclaredMethod(setEntity, tClass);
            Method setIdMethod = value.getClass().getDeclaredMethod(setId, Long.class);
            tClass.cast(setEntityMethod.invoke(value, (T) null));
            tClass.cast(setIdMethod.invoke(value, (Long) null));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static <T> Page<T> initPage(Pageable pageable, Query<T>query, int totalCount) {

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<T> entities = query.getResultList();

        return new PageImpl<>(entities, pageable, totalCount);
    }

    public static <T> Page<T> initPage(Session session, Pageable pageable, Query<T>
            query, String counter) {

        Long totalEmployees = initQuery(Long.class, session, counter).uniqueResult();
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<T> employees = query.getResultList();

        return new PageImpl<>(employees, pageable, totalEmployees);
    }

    private DAOUtil() {}
}
