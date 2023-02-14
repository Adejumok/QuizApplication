package com.africa.quizapp.repository;

import com.africa.quizapp.models.quizModels.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findByText(String text);
}
