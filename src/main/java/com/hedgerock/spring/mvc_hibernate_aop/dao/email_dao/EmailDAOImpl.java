package com.hedgerock.spring.mvc_hibernate_aop.dao.email_dao;

import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.Email;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmailDAOImpl implements EmailDAO {

    private final SessionFactory sessionFactory;

    public EmailDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Email findCurrentEmail(Long id) {
        Session session = this.sessionFactory.getCurrentSession();

        Query<Email> emailQuery = session.createQuery(
                "FROM Email WHERE id = :id",
                Email.class
        );

        emailQuery.setParameter("id", id);

        return emailQuery.getSingleResult();
    }
}
