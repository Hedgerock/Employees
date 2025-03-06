package com.hedgerock.spring.mvc_hibernate_aop.dao.phone_dao;

import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.PhoneNumber;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PhoneDAOImpl implements  PhoneDAO {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public PhoneNumber findCurrentPhoneNumber(Long id) {
        Session session = this.sessionFactory.getCurrentSession();

        Query<PhoneNumber> phoneNumberQuery = session.createQuery(
                "FROM PhoneNumber WHERE id = :id",
                PhoneNumber.class
        );

        phoneNumberQuery.setParameter("id", id);

        return phoneNumberQuery.getSingleResult();
    }
}
