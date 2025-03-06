package com.hedgerock.spring.mvc_hibernate_aop.service.email_service;

import com.hedgerock.spring.mvc_hibernate_aop.dao.email_dao.EmailDAO;
import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.Email;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private EmailDAO emailDAO;

    @Override
    @Transactional
    public Email findCurrentEmail(Long id) {
        return this.emailDAO.findCurrentEmail(id);
    }
}
