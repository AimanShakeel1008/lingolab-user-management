package com.lingolab.usermanagement.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_language_registrations")
@Data
public class UserLanguageRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "language_id")
    private Long languageId; // This ID refers to the language in the Language Management Service

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;
}
