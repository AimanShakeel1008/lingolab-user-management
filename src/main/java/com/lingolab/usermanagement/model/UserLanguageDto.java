package com.lingolab.usermanagement.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserLanguageDto {
    private Long id;
    private LanguageDto language;
    private LocalDateTime registrationDate;
    private int progress;
}
