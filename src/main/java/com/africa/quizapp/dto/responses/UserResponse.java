package com.africa.quizapp.dto.responses;

import com.africa.quizapp.models.QuizUser;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserResponse {
    private String message;
    private FormResponse formResponse;
    private QuizUser user;
}
