package com.africa.quizapp.dto.responses;

import com.africa.quizapp.models.quizModels.Question;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class QuestionResponse {
    private String message;
    private Question question;
}
