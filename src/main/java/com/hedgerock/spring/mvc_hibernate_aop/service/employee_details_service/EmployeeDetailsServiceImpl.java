package com.hedgerock.spring.mvc_hibernate_aop.service.employee_details_service;

import com.hedgerock.spring.mvc_hibernate_aop.dao.employee_details_dao.EmployeeDetailsDAO;
import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.EmployeeDetails;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {
    @Autowired
    private EmployeeDetailsDAO employeeDetailsDAO;

    @Override
    @Transactional
    public Optional<EmployeeDetails> getEmployeeDetails(Long id) {
        return this.employeeDetailsDAO.getEmployeeDetails(id);
    }

    @Override
    @Transactional
    public EmployeeDetails saveEmployeeDetails(EmployeeDetails employeeDetails) {
        return this.employeeDetailsDAO.saveEmployeeDetails(employeeDetails);
    }
}
