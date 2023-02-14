package com.africa.quizapp.models.contactModels;

import com.africa.quizapp.models.QuizUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userEmail;
    @OneToOne
    private QuizUser quizUser;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_list_id")
    private ContactList contactList;
}
