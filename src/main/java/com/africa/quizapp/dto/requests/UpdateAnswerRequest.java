package com.africa.quizapp.dto.requests;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UpdateAnswerRequest {
    private Long id;
    private String text;
}
