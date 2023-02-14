package com.africa.quizapp.dto.responses;

import com.africa.quizapp.models.quizModels.Report;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReportResponse {
    private String message;
    private Report report;
}
