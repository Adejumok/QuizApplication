package com.africa.quizapp.dto.requests;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserRequestToTakeQuiz {
    private String userEmail;
    private String quizName;
}
