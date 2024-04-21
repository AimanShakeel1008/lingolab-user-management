package com.lingolab.usermanagement.controller;

import com.lingolab.usermanagement.model.UserLanguageDto;
import com.lingolab.usermanagement.model.UserLanguageRegistration;
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
    public ResponseEntity<UserLanguageRegistration> registerLanguage(@RequestParam Long userId, @RequestParam Long languageId) {
        System.out.println("userId:"+userId+":::"+"language id:"+languageId);
        UserLanguageRegistration registration = userLanguageRegistrationService.registerLanguage(userId, languageId);
        return ResponseEntity.ok(registration);
    }
}

