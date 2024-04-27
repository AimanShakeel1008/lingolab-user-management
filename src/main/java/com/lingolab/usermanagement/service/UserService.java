package com.lingolab.usermanagement.service;

import com.lingolab.usermanagement.model.User;
import com.lingolab.usermanagement.model.UserAllDetails;
import com.lingolab.usermanagement.model.UserAllDetailsRequest;
import com.lingolab.usermanagement.repository.UserDetailsRepository;
import com.lingolab.usermanagement.repository.UserRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final UserDetailsRepository userDetailsRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private Validator validator;

    @Autowired
    public UserService(UserRepository userRepository, UserDetailsRepository userDetailsRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerUser(User user) throws Exception {
        try {
            if (userRepository.findByUsername(user.getUsername()).isPresent()) {
                throw new Exception("Username is already taken: " + user.getUsername());
            }
            if (userRepository.findByEmail(user.getEmail()).isPresent()) {  // If checking emails
                throw new Exception("Email is already in use: " + user.getEmail());
            }
            Set<ConstraintViolation<User>> violations = validator.validate(user);

            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }

            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

        } catch (ConstraintViolationException e) {
            for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
                System.out.println(violation.getMessage()); // Or use a logger to log the error message
            }
            throw e; // Rethrow or handle it based on your application's needs
        }

        UserAllDetails userAllDetails = new UserAllDetails();

        User savedUser = userRepository.save(user);

        userAllDetails.setUser(savedUser);

        userDetailsRepository.save(userAllDetails);

        return savedUser;
    }

    public UserAllDetails saveUserDetails(UserAllDetailsRequest userAllDetailsRequest) {

        User user = userRepository.findByUsername(userAllDetailsRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("No user found with username: " + userAllDetailsRequest.getUsername()));

        Optional<UserAllDetails> existingUserDetails = userDetailsRepository.findByUserId(user.getId());

        UserAllDetails userAllDetails = existingUserDetails.orElse(new UserAllDetails());

        userAllDetails.setUser(user);
        userAllDetails.setFullName(userAllDetailsRequest.getFullName());
        userAllDetails.setDateOfBirth(userAllDetailsRequest.getDateOfBirth());
        userAllDetails.setNativeLanguageId(userAllDetailsRequest.getNativeLanguageId());
        userAllDetails.setCountryId(userAllDetailsRequest.getCountryId());

        return userDetailsRepository.save(userAllDetails);
    }

    public UserAllDetails getUserDetailsByUserName(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with username: " + username));

        return userDetailsRepository.findByUserId(user.getId()).orElseThrow(() -> new RuntimeException("User not found!"));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with username: " + username));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
