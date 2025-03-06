package com.hedgerock.spring.mvc_hibernate_aop.dao.picture_dao;

import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.Picture;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PictureDAOImpl implements PictureDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Picture savePicture(Picture picture) {
        final Session session = this.sessionFactory.getCurrentSession();
        return session.merge(picture);
    }

    @Override
    public Picture findPicture(Long id) {
        final Session session = this.sessionFactory.getCurrentSession();
        return session.find(Picture.class, id);
    }
}
