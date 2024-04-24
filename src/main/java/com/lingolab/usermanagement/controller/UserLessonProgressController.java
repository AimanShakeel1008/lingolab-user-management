package com.lingolab.usermanagement.controller;

import com.lingolab.usermanagement.model.UserLessonProgress;
import com.lingolab.usermanagement.model.UserLessonProgressRequest;
import com.lingolab.usermanagement.service.UserLessonProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserLessonProgressController {
    @Autowired
    private UserLessonProgressService userLessonProgressService;

    @GetMapping("/lessons/progress")
    public ResponseEntity<List<UserLessonProgress>> getUserLessonProgress(@RequestParam String username) {

        List<UserLessonProgress> userLessonProgressList = userLessonProgressService.getUserLessonProgress(username);

        return ResponseEntity.ok(userLessonProgressList);

    }

    @PostMapping("/lessons/complete")
    public ResponseEntity<UserLessonProgress> completeLesson(@RequestBody UserLessonProgressRequest userLessonProgressRequest) {
        UserLessonProgress userLessonProgress = userLessonProgressService.markLessonAsCompleted(userLessonProgressRequest.getUsername(),
                userLessonProgressRequest.getLessonId(),
                userLessonProgressRequest.getProgressPercent());

        return ResponseEntity.ok(userLessonProgress);
    }
}

