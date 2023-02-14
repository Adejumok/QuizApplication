package com.africa.quizapp.models.quizModels;

import com.africa.quizapp.models.QuizUser;
import com.africa.quizapp.models.quizModels.Quiz;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToOne
    private QuizUser quizUser;
}