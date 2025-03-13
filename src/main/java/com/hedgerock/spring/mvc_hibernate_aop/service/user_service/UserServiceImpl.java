package com.hedgerock.spring.mvc_hibernate_aop.service.user_service;

import com.hedgerock.spring.mvc_hibernate_aop.dao.user_dao.UserDAO;
import com.hedgerock.spring.mvc_hibernate_aop.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public Optional<User> getUser(String username) {
        return this.userDAO.getUser(username);
    }

    @Override
    @Transactional
    public Page<User> getUsers(Pageable pageable) {
        return this.userDAO.getUsers(pageable);
    }
}
