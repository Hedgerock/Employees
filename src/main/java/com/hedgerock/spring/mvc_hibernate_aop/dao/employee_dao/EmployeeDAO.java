package com.hedgerock.spring.mvc_hibernate_aop.dao.employee_dao;

import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeDAO {

    Page<Employee> getAllEmployees(Pageable pageable);
    Page<Employee> getAllEmployees(Pageable pageable, boolean isLookingForFired);
    Employee saveNewEmployee(Employee employee, Long depId, Long cityId, Long natId);
    Employee saveNewEmployee(Employee employee);
    Employee findEmployee(Long id);
    void deleteEmployee(Employee employee);
    Page<Employee> getSearchedEmployees(String search, Pageable pageable);
    Page<Employee> getSearchedEmployees(String search, Pageable pageable,  boolean isLookingForFired);
    Page<Employee> findEmployeesInCurrentPlace(Long id, String search, String tableFieldName, Pageable pageable);
    Page<Employee> findEmployeesInCurrentPlace(Long id, String tableFieldName, Pageable pageable);
    List<Employee> findEmployeeInCurrentPlace(Long placeId, String entityName);
    List<Employee> getSpecificEmployees(String tableFieldName);
    List<Employee> getSelectedEmployees(List<Long> id);
    void saveAllEmployees(List<Employee> employees);
}
