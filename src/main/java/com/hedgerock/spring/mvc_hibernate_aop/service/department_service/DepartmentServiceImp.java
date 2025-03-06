package com.hedgerock.spring.mvc_hibernate_aop.service.department_service;

import com.hedgerock.spring.mvc_hibernate_aop.dao.department_dao.DepartmentDAO;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Department;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImp implements DepartmentService {
    @Autowired
    DepartmentDAO departmentDao;

    @Override
    @Transactional
    public Optional<Department> findCurrentDepartment(String name) {
        return this.departmentDao.findCurrentDepartmentByName(name);
    }

    @Override
    @Transactional
    public List<Department> getAllDepartments() {
        return this.departmentDao.getAllDepartments();
    }

    @Override
    @Transactional
    public Optional<Department> findCurrentDepartmentById(Long id) {
        return this.departmentDao.findCurrentDepartmentById(id);
    }

    @Override
    @Transactional
    public Department saveCurrentDepartment(Department department) {
        return this.departmentDao.initDepartmentChanges(department);
    }

    @Override
    @Transactional
    public void deleteCurrentDepartment(Department department) {
        this.departmentDao.deleteCurrentDepartment(department);
    }
}
