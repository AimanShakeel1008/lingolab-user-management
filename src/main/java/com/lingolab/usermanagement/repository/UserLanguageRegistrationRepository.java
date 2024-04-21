package com.lingolab.usermanagement.repository;

import com.lingolab.usermanagement.model.UserLanguageRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserLanguageRegistrationRepository extends JpaRepository<UserLanguageRegistration, Long> {
    List<UserLanguageRegistration> findByUserId(Long userId);
    boolean existsByUserIdAndLanguageId(Long userId, Long languageId);

    Optional<UserLanguageRegistration> findByUserIdAndLanguageId(Long userId, Long languageId);


}

