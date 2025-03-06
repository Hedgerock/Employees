package com.hedgerock.spring.mvc_hibernate_aop.dao.email_dao;

import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.Email;

public interface EmailDAO {
    Email findCurrentEmail(Long id);
}
