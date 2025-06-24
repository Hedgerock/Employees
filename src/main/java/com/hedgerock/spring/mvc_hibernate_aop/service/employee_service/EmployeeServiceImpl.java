package com.hedgerock.spring.mvc_hibernate_aop.service.employee_service;

import com.hedgerock.spring.mvc_hibernate_aop.dao.employee_dao.EmployeeDAO;
import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    @Transactional
    public Page<Employee> getAllEmployees(Pageable pageable) {
        return this.employeeDAO.getAllEmployees(pageable);
    }

    @Override
    @Transactional
    public Page<Employee> getAllEmployees(Pageable pageable, boolean isLookingForFired) {
        return this.employeeDAO.getAllEmployees(pageable, isLookingForFired);
    }

    @Override
    @Transactional
    public Employee saveCurrentEmployee(Employee employee, Long depId, Long cityId, Long natId) {
        return this.employeeDAO.saveNewEmployee(employee, depId, cityId, natId);
    }

    @Override
    @Transactional
    public Employee saveCurrentEmployee(Employee employee) {
        return this.employeeDAO.saveNewEmployee(employee);
    }

    @Override
    @Transactional
    public Employee findEmployee(Long id) {
        return this.employeeDAO.findEmployee(id);
    }

    @Override
    @Transactional
    public void deleteEmployee(Employee employee) {
        this.employeeDAO.deleteEmployee(employee);
    }

    @Override
    @Transactional
    public Page<Employee> getSearchedEmployees(String search, Pageable pageable) {

        return this.employeeDAO.getSearchedEmployees(search, pageable);
    }

    @Override
    @Transactional
    public Page<Employee> getSearchedEmployees(String search, Pageable pageable, boolean isLookingForFired) {
        return this.employeeDAO.getSearchedEmployees(search, pageable, isLookingForFired );
    }

    @Override
    @Transactional
    public Page<Employee> findEmployeesInCurrentPlace(Long id, String tableFieldName, Pageable pageable) {
        return this.employeeDAO.findEmployeesInCurrentPlace(id, tableFieldName, pageable);
    }

    @Override
    @Transactional
    public Page<Employee> findEmployeesInCurrentPlace(Long id, String search, String tableName, Pageable pageable) {
        return this.employeeDAO.findEmployeesInCurrentPlace(id, search, tableName, pageable);
    }

    @Override
    @Transactional
    public List<Employee> findEmployeeInCurrentPlace(Long placeId, String entityName) {
        return this.employeeDAO.findEmployeeInCurrentPlace(placeId, entityName);
    }

    @Override
    @Transactional
    public List<Employee> getSpecificEmployees(String tableFieldName) {
        return this.employeeDAO.getSpecificEmployees(tableFieldName);
    }

    @Override
    @Transactional
    public List<Employee> getSelectedEmployees(List<Long> id) {
        return this.employeeDAO.getSelectedEmployees(id);
    }

    @Override
    @Transactional
    public void saveAllEmployees(List<Employee> employees) {
        this.employeeDAO.saveAllEmployees(employees);
    }
}
