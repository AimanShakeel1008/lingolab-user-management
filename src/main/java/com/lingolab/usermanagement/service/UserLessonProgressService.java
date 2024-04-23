package com.lingolab.usermanagement.service;


import com.lingolab.usermanagement.model.User;
import com.lingolab.usermanagement.model.UserLessonProgress;
import com.lingolab.usermanagement.repository.UserLessonProgressRepository;
import com.lingolab.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserLessonProgressService {
    @Autowired
    private UserLessonProgressRepository userLessonProgressRepository;
    @Autowired
    private UserRepository userRepository;

    public UserLessonProgress markLessonAsCompleted(String username, Long lessonId, int progressPercent) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        Optional<UserLessonProgress> existingProgress = userLessonProgressRepository.findByUserIdAndLessonId(user.getId(), lessonId);


        UserLessonProgress userLessonProgress = existingProgress.orElse(new UserLessonProgress());
        userLessonProgress.setUserId(user.getId());
        userLessonProgress.setLessonId(lessonId);
        userLessonProgress.setProgressPercent(progressPercent);
        userLessonProgress.setCompletionDate(LocalDateTime.now());

        return userLessonProgressRepository.save(userLessonProgress);
    }
}
