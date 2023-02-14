package com.africa.quizapp.repository;

import com.africa.quizapp.models.contactModels.Form;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository<Form, Long> {
}
