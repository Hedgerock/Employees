package com.hedgerock.spring.mvc_hibernate_aop.service.email_service;

import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.Email;

public interface EmailService {
    Email findCurrentEmail(Long id);
}
