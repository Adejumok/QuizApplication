package com.africa.quizapp.repository;

import com.africa.quizapp.models.QuizUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<QuizUser, Long> {
    Optional<QuizUser> findByEmail(String email);
}
