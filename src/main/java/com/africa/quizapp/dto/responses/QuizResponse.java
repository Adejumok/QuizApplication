package com.africa.quizapp.dto.responses;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class QuizResponse {
    private String message;
    private int quizScore;
}
