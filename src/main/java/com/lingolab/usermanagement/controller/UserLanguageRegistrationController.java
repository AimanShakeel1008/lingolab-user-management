package com.lingolab.usermanagement.controller;

import com.lingolab.usermanagement.model.UserLanguageDto;
import com.lingolab.usermanagement.model.UserLanguageRegistration;
import com.lingolab.usermanagement.model.UserLanguageRegistrationRequest;
import com.lingolab.usermanagement.service.UserLanguageRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/languages")
public class UserLanguageRegistrationController {
    @Autowired
    private UserLanguageRegistrationService userLanguageRegistrationService;

    @GetMapping("/registrations")
    public ResponseEntity<List<UserLanguageDto>> getRegistrationsByUsername(@RequestParam String username) {
        List<UserLanguageDto> registrations = userLanguageRegistrationService.findRegistrationsByUsername(username);
        if (registrations.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(registrations);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerLanguage(@RequestBody UserLanguageRegistrationRequest userLanguageRegistrationRequest) {
        try {
            UserLanguageRegistration registration = userLanguageRegistrationService.registerLanguage(userLanguageRegistrationRequest.getUsername(),
                    userLanguageRegistrationRequest.getLanguageId());
            return ResponseEntity.ok(registration);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/unregister/{username}/{languageId}")
    public ResponseEntity<?> unregisterLanguage(@PathVariable String username, @PathVariable Long languageId) {
        try {
            userLanguageRegistrationService.unregisterLanguage(username, languageId);
            return ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

