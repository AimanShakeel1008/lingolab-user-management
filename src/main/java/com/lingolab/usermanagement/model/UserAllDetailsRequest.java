package com.lingolab.usermanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.Date;

@Data
public class UserAllDetailsRequest {
    private String username;
    private String fullName;
    private Date dateOfBirth;
    private Long countryId;
    private Long nativeLanguageId;
}
