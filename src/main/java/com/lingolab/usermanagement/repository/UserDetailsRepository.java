package com.lingolab.usermanagement.repository;

import com.lingolab.usermanagement.model.UserAllDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserAllDetails, Long> {
    Optional<UserAllDetails> findByUserId(Long userId);
}