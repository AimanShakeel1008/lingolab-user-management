package com.lingolab.usermanagement.repository;

import com.lingolab.usermanagement.model.UserLanguageRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserLanguageRegistrationRepository extends JpaRepository<UserLanguageRegistration, Long> {
    List<UserLanguageRegistration> findByUserId(Long userId);
}

