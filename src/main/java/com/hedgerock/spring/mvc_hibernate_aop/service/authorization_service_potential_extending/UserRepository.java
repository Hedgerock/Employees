package com.hedgerock.spring.mvc_hibernate_aop.service.authorization_service_potential_extending;

import com.hedgerock.spring.mvc_hibernate_aop.entity.authorization_potential_extending.CurrentUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<CurrentUser, Long> {
    Optional<CurrentUser> findByUsername(String username);
}
