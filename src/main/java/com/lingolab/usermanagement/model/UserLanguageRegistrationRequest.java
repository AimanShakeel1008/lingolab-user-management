package com.lingolab.usermanagement.model;

import lombok.Data;

@Data
public class UserLanguageRegistrationRequest {
    private String username;
    private Long languageId;
}
