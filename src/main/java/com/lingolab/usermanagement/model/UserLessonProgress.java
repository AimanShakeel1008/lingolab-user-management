package com.lingolab.usermanagement.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_lesson_progress")
@Data
public class UserLessonProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "lesson_id")
    private Long lessonId;

    @Column(name = "progress_percent")
    private int progressPercent;

    @Column(name = "completion_date")
    private LocalDateTime completionDate;

}

