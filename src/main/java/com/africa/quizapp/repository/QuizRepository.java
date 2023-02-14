package com.africa.quizapp.repository;

import com.africa.quizapp.models.quizModels.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Optional<Quiz> findByName(String name);
}
