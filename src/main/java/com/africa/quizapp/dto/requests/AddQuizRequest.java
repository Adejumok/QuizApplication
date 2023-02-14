package com.africa.quizapp.dto.requests;

import com.africa.quizapp.models.quizModels.Question;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AddQuizRequest {
    private String name;
    private List<Question> questions;
}
