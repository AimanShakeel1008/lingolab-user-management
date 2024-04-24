package com.lingolab.usermanagement.repository;

import com.lingolab.usermanagement.model.UserLessonProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserLessonProgressRepository extends JpaRepository<UserLessonProgress, Long> {
    Optional<UserLessonProgress> findByUserIdAndLessonId(Long userId, Long lessonId);
     List<UserLessonProgress> findByUserId(Long userId);
}
