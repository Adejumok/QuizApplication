package com.africa.quizapp.repository;

import com.africa.quizapp.models.contactModels.Form;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FormRepository extends JpaRepository<Form, Long> {
    Optional<Form> findByUserEmail(String userEmail);
}
