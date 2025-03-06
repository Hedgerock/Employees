package com.hedgerock.spring.mvc_hibernate_aop.service.phoneService;

import com.hedgerock.spring.mvc_hibernate_aop.dao.phone_dao.PhoneDAO;
import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.PhoneNumber;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneDAO phoneDAO;


    @Override
    @Transactional
    public PhoneNumber findCurrentPhoneNumber(Long id) {
        return this.phoneDAO.findCurrentPhoneNumber(id);
    }
}
