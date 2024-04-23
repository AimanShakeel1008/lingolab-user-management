package com.lingolab.usermanagement.model;

import lombok.Data;

@Data
public class UserLessonProgressRequest {
    private String username;
    private Long lessonId;
    private int progressPercent;
}
