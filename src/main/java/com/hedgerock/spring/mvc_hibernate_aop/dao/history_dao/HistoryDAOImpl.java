package com.hedgerock.spring.mvc_hibernate_aop.dao.history_dao;

import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.revisions.CurrentPlaceRevisionDTO;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.single_element.CurrentPlaceDTO;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.single_element.EmployeeDTO;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.revisions.EmployeeRevisionDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Method;
import java.sql.Date;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.getActualTimeValue;

@Repository
public class HistoryDAOImpl implements HistoryDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private static final String TEMPLATE_OF_EMPLOYEE_REVISION_QUERY = "SELECT e.id, e.REV, e.REVTYPE, e.department_id, e.employee_details_id, " +
            "e.first_name, e.hire_date, e.last_name, e.middle_name, e.salary, r.REVTSTMP, d.name " +
            "FROM employees_aud e " +
            "JOIN revinfo r ON e.REV = r.REV " +
            "LEFT JOIN departments d ON e.department_id = d.id " +
            "WHERE e.id = :employeeId";

    @Override
    public Page<EmployeeRevisionDTO> getEmployeeRevisions(Long employeeId, Pageable pageable, boolean isLookingForFired) {
        Session session = this.sessionFactory.getCurrentSession();

        String queryStr = isLookingForFired
                ? TEMPLATE_OF_EMPLOYEE_REVISION_QUERY + " AND e.fire_date IS NOT NULL"
                : TEMPLATE_OF_EMPLOYEE_REVISION_QUERY + " AND e.fire_date IS NULL";

        Query<Object[]> query = session.createNativeQuery(queryStr, Object[].class);
        query.setParameter("employeeId", employeeId);

        int total = (int) query.getResultCount();

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<Object[]> resultList = query.getResultList();
        List<EmployeeRevisionDTO> revisions = new ArrayList<>();
        EmployeeRevisionDTO previousDto = null;

        for (Object[] row : resultList) {
            EmployeeRevisionDTO dto = setParameters(row, previousDto);
            revisions.add(dto);
            previousDto = dto;
        }

        return new PageImpl<>(revisions, pageable, total);
    }

    @Override
    public Page<EmployeeDTO> getEmployeesWithHistory(Pageable pageable) {
        Session session = this.sessionFactory.getCurrentSession();

        Query<Object[]> query = session.createNativeQuery(
                "SELECT e.id, e.first_name, e.last_name, e.middle_name, e.department_id, d.name, " +
                        "MAX(r.REVTSTMP) AS previousChanges " +
                        "FROM employees e " +
                        "JOIN employees_aud ea ON e.id = ea.id " +
                        "LEFT JOIN departments d ON e.department_id = d.id " +
                        "JOIN revinfo r ON ea.REV = r.REV " +
                        "WHERE e.id IN (SELECT DISTINCT id FROM employees_aud) " +
                        "AND e.fire_date IS NULL " +
                        "GROUP BY e.id, e.first_name, e.last_name, e.middle_name, e.department_id, " +
                        "e.employee_details_id, e.hire_date, e.salary, d.name " +
                        "ORDER BY previousChanges DESC",
                Object[].class
        );

        int total = (int) query.getResultCount();

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<Object[]> resultList = query.getResultList();
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();

        for(Object[] row: resultList) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(((Number) row[0]).longValue());
            employeeDTO.setFirstName((String) row[1]);
            employeeDTO.setLastName((String) row[2]);
            employeeDTO.setMiddleName((String) row[3]);
            employeeDTO.setDepartmentId(row[4] != null ? ((Number) row[4]).longValue() : null);
            employeeDTO.setDepartmentName((String) row[5]);
            String modifiedChanges = getActualTimeValue((Long) row[6], employeeDTO);
            employeeDTO.setPreviousShortChanges(modifiedChanges);

            employeeDTOS.add(employeeDTO);
        }

        return new PageImpl<>(employeeDTOS, pageable, total);
    }

    @Override
    public Page<CurrentPlaceRevisionDTO> getCurrentPlaceRevision(
            Long currentPlaceId, String entityName, Pageable pageable) {
        Session session = this.sessionFactory.getCurrentSession();

        String queryStr = getQueryRevisionListForCurrentPlace(entityName);

        Query<Object[]> query = session.createNativeQuery(queryStr, Object[].class);
        query.setParameter("currentPlaceId", currentPlaceId);

        int total = (int) query.getResultCount();

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<Object[]> resultList = query.getResultList();
        List<CurrentPlaceRevisionDTO> revisions = new ArrayList<>();
        CurrentPlaceRevisionDTO previousDto = null;

        for (Object[] row : resultList) {
            CurrentPlaceRevisionDTO dto = setParameters(row, previousDto);
            revisions.add(dto);
            previousDto = dto;
        }

        return new PageImpl<>(revisions, pageable, total);
    }

    @Override
    public Page<CurrentPlaceDTO> getCurrentPlaceHistory(String entityName, Pageable pageable) {
        Session session = this.sessionFactory.getCurrentSession();

        String query = getQueryForCurrentPlace(entityName);

        Query<Object[]> currentQuery = session.createNativeQuery(query, Object[].class);

        int total = (int) currentQuery.getResultCount();

        currentQuery.setFirstResult((int) pageable.getOffset());
        currentQuery.setMaxResults(pageable.getPageSize());

        List<Object[]> departmentsHistory = currentQuery.getResultList();
        List<CurrentPlaceDTO> currentPlaceDTOS = new ArrayList<>();

        for (Object[] row: departmentsHistory) {
            CurrentPlaceDTO currentPlaceDTO = new CurrentPlaceDTO();

            currentPlaceDTO.setId(setCurrentFiled(Long.class, row[0]));
            currentPlaceDTO.setName(setCurrentFiled(String.class, row[1]));
            currentPlaceDTO.setMinSalary(setCurrentFiled(Integer.class, row[2]));
            currentPlaceDTO.setMaxSalary(setCurrentFiled(Integer.class, row[3]));
            currentPlaceDTO.setTotalEmployees(setCurrentFiled(Integer.class, row[4]));

            String modifiedChanges = getActualTimeValue((Long) row[5], currentPlaceDTO);
            currentPlaceDTO.setPreviousShortChanges(modifiedChanges);
            currentPlaceDTOS.add(currentPlaceDTO);
        }

        return new PageImpl<>(currentPlaceDTOS, pageable, total);
    }

    private String getQueryForCurrentPlace(String entityName) {
        char entityTitle = entityName.charAt(0);
        String prefix =  entityTitle + ".";

        return "SELECT " +
                prefix + "id, " +
                prefix + "name, " +
                prefix + "min_salary, " +
                prefix + "max_salary, " +
                prefix + "total_employees, " +
                "MAX(r.REVTSTMP) AS previousChanges " +
                "FROM " +
                entityName + " " + entityTitle + " " +
                "JOIN " +
                entityName + "_aud ca ON " +
                prefix + "id = ca.id " +
                "JOIN revinfo r ON ca.REV = r.REV " +
                "WHERE " +
                prefix + "id IN " +
                "(SELECT DISTINCT id FROM " + entityName + "_aud) " +
                "GROUP BY " +
                prefix + "id, " +
                prefix + "name, " +
                prefix + "min_salary, " +
                prefix + "max_salary, " +
                prefix + "total_employees " +
                "ORDER BY previousChanges DESC";
    }

    private String getQueryRevisionListForCurrentPlace(String entityName) {
        char title = entityName.charAt(0);
        String prefix = title + ".";

        return "SELECT " +
                prefix + "id, " +
                prefix + "REV, " +
                prefix + "REVTYPE, " +
                prefix + "name, " +
                prefix + "min_salary, " +
                prefix + "max_salary, " +
                prefix + "total_employees, " +
                "r.REVTSTMP " +
                "FROM " +
                entityName + "_aud " + title + " " +
                "JOIN revinfo r ON " +
                prefix + "REV = r.REV " +
                "WHERE " +
                prefix + "id = :currentPlaceId ";

    }

    private EmployeeRevisionDTO setParameters(Object[] row, EmployeeRevisionDTO previousDto) {
        EmployeeRevisionDTO dto = new EmployeeRevisionDTO();
        dto.setId(setCurrentFiled(Long.class, row[0]));
        dto.setRev(setCurrentFiled(Long.class, row[1]));
        dto.setRevType(setCurrentFiled(Integer.class, row[2]));
        dto.setDepartmentId(setCurrentFiled(Long.class, row[3]));
        dto.setEmployeeDetailsId(setCurrentFiled(Long.class, row[4]));
        dto.setFirstName(setCurrentFiled(String.class, row[5]));
        dto.setHireDate(setCurrentFiled(LocalDate.class, row[6]));
        dto.setLastName(setCurrentFiled(String.class, row[7]));
        dto.setMiddleName(setCurrentFiled(String.class, row[8]));
        dto.setSalary(setCurrentFiled(Integer.class, row[9]));
        initDateFormat(row[10], dto);
        dto.setDepartmentName(setCurrentFiled(String.class, row[11]));

        if (dto.getRevType() == 1 && previousDto != null) {
            dto.setOldFirstName(previousDto.getFirstName());
            dto.setOldLastName(previousDto.getLastName());
            dto.setOldMiddleName(previousDto.getMiddleName());
            dto.setOldDepartmentId(previousDto.getDepartmentId());
            dto.setOldHireDate(previousDto.getHireDate());
            dto.setOldSalary(previousDto.getSalary());
            dto.setOldDepartmentName(previousDto.getDepartmentName());
        }

        return dto;
    }

    private <T> T setCurrentFiled(Class<T> curClass, Object row) {
        if (curClass == Long.class) {
            return curClass.cast(row != null ? ((Number) row).longValue() : 0);
        } else if (curClass == Integer.class) {
            return curClass.cast(row != null ? ((Number) row).intValue() : 0);
        } else if (curClass == LocalDate.class) {
            return curClass.cast(row != null ? ((Date) row).toLocalDate() : null);
        } else {
            return curClass.cast(row);
        }
    }

    private<T> void initDateFormat(Object row, T dto) {
        try {
            Method method = dto.getClass().getDeclaredMethod("setRevisionTimestamp", String.class);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            Long longValue = setCurrentFiled(Long.class, row);
            LocalDateTime timestamp = LocalDateTime.ofInstant(Instant.ofEpochMilli(longValue), ZoneId.systemDefault());
            method.invoke(dto, timestamp.format(formatter));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private CurrentPlaceRevisionDTO setParameters(Object[] row, CurrentPlaceRevisionDTO previousDto) {
        CurrentPlaceRevisionDTO dto = new CurrentPlaceRevisionDTO();
        dto.setId(setCurrentFiled(Long.class, row[0]));
        dto.setRev(setCurrentFiled(Long.class, row[1]));
        dto.setRevType(setCurrentFiled(Integer.class, row[2]));
        dto.setName(setCurrentFiled(String.class, row[3]));
        dto.setMinSalary(setCurrentFiled(Integer.class, row[4]));
        dto.setMaxSalary(setCurrentFiled(Integer.class, row[5]));
        dto.setTotalEmployees(setCurrentFiled(Integer.class, row[6]));
        initDateFormat(row[7], dto);

        if (dto.getRevType() == 1 && previousDto != null) {
            dto.setOldName(previousDto.getName());
            dto.setOldMinSalary(previousDto.getMinSalary());
            dto.setOldMaxSalary(previousDto.getMaxSalary());
            dto.setOldTotalEmployees(previousDto.getTotalEmployees());
        }

        return dto;
    }
}
