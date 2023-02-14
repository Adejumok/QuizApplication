package com.africa.quizapp.dto.requests;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class FormRequest {
    private String userEmail;
}
