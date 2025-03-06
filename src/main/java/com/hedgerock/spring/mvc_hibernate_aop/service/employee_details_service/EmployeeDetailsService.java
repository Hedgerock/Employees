package com.hedgerock.spring.mvc_hibernate_aop.service.employee_details_service;

import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.EmployeeDetails;

import java.util.Optional;

public interface EmployeeDetailsService {
    Optional<EmployeeDetails> getEmployeeDetails(Long id);
    EmployeeDetails saveEmployeeDetails(EmployeeDetails employeeDetails);
}
