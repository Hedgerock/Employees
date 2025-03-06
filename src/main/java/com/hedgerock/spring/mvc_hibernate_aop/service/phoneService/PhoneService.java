package com.hedgerock.spring.mvc_hibernate_aop.service.phoneService;

import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.PhoneNumber;

public interface PhoneService {
    PhoneNumber findCurrentPhoneNumber(Long id);
}
