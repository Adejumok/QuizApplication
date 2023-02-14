package com.africa.quizapp.repository;

import com.africa.quizapp.models.quizModels.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
