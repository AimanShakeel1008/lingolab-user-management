package com.lingolab.usermanagement.service;

import com.lingolab.usermanagement.model.LanguageDto;
import com.lingolab.usermanagement.model.User;
import com.lingolab.usermanagement.model.UserLanguageDto;
import com.lingolab.usermanagement.model.UserLanguageRegistration;
import com.lingolab.usermanagement.repository.UserLanguageRegistrationRepository;
import com.lingolab.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserLanguageRegistrationService {

    private UserLanguageRegistrationRepository registrationRepository;
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserLanguageRegistrationService(UserLanguageRegistrationRepository registrationRepository, RestTemplate restTemplate) {
        this.registrationRepository = registrationRepository;
        this.restTemplate = restTemplate;
    }

    private final String LANGUAGE_SERVICE_BASE_URL = "http://localhost:8081/api/languages/";

    public List<UserLanguageDto> findRegistrationsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        List<UserLanguageRegistration> registrations = registrationRepository.findByUserId(user.getId());
        return registrations.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private UserLanguageDto convertToDTO(UserLanguageRegistration registration) {
        UserLanguageDto dto = new UserLanguageDto();
        dto.setId(registration.getId());
        dto.setRegistrationDate(registration.getRegistrationDate());
        String languageName = fetchLanguageName(registration.getLanguageId());
        LanguageDto languageDto = new LanguageDto();
        languageDto.setId(registration.getId());
        languageDto.setName(languageName);
        dto.setLanguage(languageDto);
        dto.setProgress(50);  // Dummy progress
        return dto;
    }

    private String fetchLanguageName(Long languageId) {
        ResponseEntity<LanguageDto> response = restTemplate.getForEntity(LANGUAGE_SERVICE_BASE_URL + languageId, LanguageDto.class);
        return response.getBody() != null ? response.getBody().getName() : "Unknown";
    }


    public UserLanguageRegistration registerLanguage(Long userId, Long languageId) {
        // Optionally, verify the language exists by calling the Language Management Service
        if (!languageExists(languageId)) {
            throw new IllegalArgumentException("Language with ID " + languageId + " does not exist.");
        }

        UserLanguageRegistration registration = new UserLanguageRegistration();
        registration.setUserId(userId);
        registration.setLanguageId(languageId);
        registration.setRegistrationDate(LocalDateTime.now());
        return registrationRepository.save(registration);
    }

    private boolean languageExists(Long languageId) {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(LANGUAGE_SERVICE_BASE_URL + languageId, String.class);
            return response.getStatusCode() == HttpStatus.OK;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }
}

