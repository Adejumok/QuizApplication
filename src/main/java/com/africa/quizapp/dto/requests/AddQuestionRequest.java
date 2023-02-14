package com.africa.quizapp.dto.requests;

import com.africa.quizapp.models.enums.Category;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AddQuestionRequest {
    private String text;
    private Category category;
}
