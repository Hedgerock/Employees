package com.hedgerock.spring.mvc_hibernate_aop.dao.user_dao;

import com.hedgerock.spring.mvc_hibernate_aop.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.DAOUtil.initPage;

@Repository
public class UserDAOImpl implements UserDAO {
    private static final String USER_JOINS = "LEFT JOIN FETCH u.userDetails " +
            "JOIN FETCH u.authorities ";

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Optional<User> getUser(String username) {
        final Session session = this.sessionFactory.getCurrentSession();

        String hql = "SELECT u FROM User u " + USER_JOINS + "WHERE u.username = :name";

        Query<User> userQuery = session.createQuery(hql, User.class);

        userQuery.setParameter("name", username);

        return Optional.ofNullable(userQuery.uniqueResult());
    }

    @Override
    public Page<User> getUsers(Pageable pageable) {
        final Session session = this.sessionFactory.getCurrentSession();

        String hql = "SELECT u FROM User u " + USER_JOINS;
        String totalHql = "SELECT COUNT(username) FROM User";

        final Query<User> userQuery = session.createQuery(hql, User.class);

        return initPage(session, pageable, userQuery, totalHql);
    }
}
