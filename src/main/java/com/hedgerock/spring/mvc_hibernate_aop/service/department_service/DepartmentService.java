package com.hedgerock.spring.mvc_hibernate_aop.service.department_service;

import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    Optional<Department> findCurrentDepartment(String name);
    List<Department> getAllDepartments();
    Optional<Department> findCurrentDepartmentById(Long id);
    Department saveCurrentDepartment(Department department);
    void deleteCurrentDepartment(Department department);
}
