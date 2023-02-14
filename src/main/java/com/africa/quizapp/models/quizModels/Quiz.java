package com.africa.quizapp.models.quizModels;

import com.africa.quizapp.models.QuizUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "quiz", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Question> questions;
    private int grade;
    @ManyToOne(fetch = FetchType.LAZY)
    private QuizUser quizUser;
}
