package com.africa.quizapp.models.quizModels;

import com.africa.quizapp.models.enums.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull()
    private String text;
    @OneToMany(cascade = CascadeType.ALL,
    mappedBy = "question", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Answer> answers;
    @Enumerated
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;
    @OneToOne
    @JsonIgnore
    private Answer correctAnswer;
}
