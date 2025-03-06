package com.hedgerock.spring.mvc_hibernate_aop.dao.phone_dao;

import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.PhoneNumber;

public interface PhoneDAO {
    PhoneNumber findCurrentPhoneNumber(Long id);
}
