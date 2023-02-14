package com.africa.quizapp.models;

import com.africa.quizapp.models.contactModels.Form;
import com.africa.quizapp.models.quizModels.Quiz;
import com.africa.quizapp.models.quizModels.Report;
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
public class QuizUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String address;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Quiz> quizzes;
    @OneToOne
    private Report report;
    @OneToOne
    private Form form;
    }
