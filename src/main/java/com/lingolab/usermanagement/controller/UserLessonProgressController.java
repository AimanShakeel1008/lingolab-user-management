package com.lingolab.usermanagement.controller;

import com.lingolab.usermanagement.model.UserLessonProgress;
import com.lingolab.usermanagement.model.UserLessonProgressRequest;
import com.lingolab.usermanagement.service.UserLessonProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/lessons")
public class UserLessonProgressController {
    @Autowired
    private UserLessonProgressService userLessonProgressService;

    @PostMapping("/complete")
    public ResponseEntity<UserLessonProgress> completeLesson(@RequestBody UserLessonProgressRequest userLessonProgressRequest) {
        UserLessonProgress userLessonProgress = userLessonProgressService.markLessonAsCompleted(userLessonProgressRequest.getUsername(),
                userLessonProgressRequest.getLessonId(),
                userLessonProgressRequest.getProgressPercent());

        return ResponseEntity.ok(userLessonProgress);
    }
}

