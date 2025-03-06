package com.hedgerock.spring.mvc_hibernate_aop.dao.department_dao;

import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentDAO {
    Optional<Department> findCurrentDepartmentByName(String name);
    List<Department> getAllDepartments();
    Optional<Department> findCurrentDepartmentById(Long id);
    Department initDepartmentChanges(Department department);
    void deleteCurrentDepartment(Department department);
}
