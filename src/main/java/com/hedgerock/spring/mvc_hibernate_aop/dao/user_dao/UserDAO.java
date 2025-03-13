package com.hedgerock.spring.mvc_hibernate_aop.dao.user_dao;

import com.hedgerock.spring.mvc_hibernate_aop.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserDAO {

    Optional<User> getUser(String username);
    Page<User> getUsers(Pageable pageable);

}
