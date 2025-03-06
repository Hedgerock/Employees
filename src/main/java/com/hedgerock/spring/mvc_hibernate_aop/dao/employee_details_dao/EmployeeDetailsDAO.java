package com.hedgerock.spring.mvc_hibernate_aop.dao.employee_details_dao;

import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.EmployeeDetails;

import java.util.Optional;

public interface EmployeeDetailsDAO {
    Optional<EmployeeDetails> getEmployeeDetails(Long id);
    EmployeeDetails saveEmployeeDetails(EmployeeDetails employeeDetails);
}
