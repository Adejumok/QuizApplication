package com.africa.quizapp.repository;

import com.africa.quizapp.models.quizModels.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> findByText(String text);
}
