package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.AddQuestionRequest;
import com.africa.quizapp.dto.requests.UpdateQuestionRequest;
import com.africa.quizapp.dto.responses.AnswerResponse;
import com.africa.quizapp.dto.responses.QuestionResponse;
import com.africa.quizapp.models.enums.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class QuestionServiceTest {
    @Autowired
    private QuestionService questionService;
    private AddQuestionRequest request;
    private UpdateQuestionRequest updateQuestionRequest;
    @BeforeEach
    void setUp(){
        request = AddQuestionRequest
                .builder()
                .text("How would you describe a Metaverse?")
                .category(Category.BEGINNER)
                .build();

        updateQuestionRequest = UpdateQuestionRequest.builder()
                .id(1L)
                .text("What is a noun?")
                .category(Category.INTERMEDIATE)
                .build();
    }

    @Test
    void addQuestionTest(){
        CompletableFuture<QuestionResponse> response = questionService.addQuestionResponse(request);
        QuestionResponse jointResponse =  response.join();
        assertThat(response).isNotNull();
        assertEquals(jointResponse.getMessage(), "Question created successfully.");
    }

    @Test
    void addSameQuestionRequestGetErrorTest(){
        CompletableFuture<QuestionResponse> response = questionService.addQuestionResponse(request);
        assertThat(response).isNotNull();
        assertThrows(CompletionException.class, response::join);
    }

    @Test
    void addAnswerToQuestionTest(){
        CompletableFuture<QuestionResponse> response = questionService.addAnswerToQuestionResponse(2L, 1L);
        QuestionResponse jointResponse =  response.join();
        assertThat(response).isNotNull();
        assertEquals(jointResponse.getMessage(), "Answer successfully added to Question with id '"+1L+"'!");
    }

    @Test
    void addCorrectAnswerTest(){
        CompletableFuture<QuestionResponse> response = questionService.addCorrectAnswerResponse(1L, 1L);
        QuestionResponse jointResponse =  response.join();
        assertThat(response).isNotNull();
        assertEquals(jointResponse.getMessage(), "Answer with id '"+1L+"' successfully " +
                "added to this Question as the correct answer!");
    }

    @Test
    void checkForCorrectAnswerTest(){
        CompletableFuture<QuestionResponse> response = questionService.checkCorrectAnswerResponse(1L, 1L);
        QuestionResponse jointResponse =  response.join();
        assertThat(response).isNotNull();
        assertEquals(jointResponse.getMessage(), "Correct!");
    }

    @Test
    void checkForWrongAnswerTest(){
        CompletableFuture<QuestionResponse> response = questionService.checkCorrectAnswerResponse(1L, 2L);
        QuestionResponse jointResponse =  response.join();
        assertThat(response).isNotNull();
        assertEquals(jointResponse.getMessage(), "Oops! Wrong.");
    }

    @Test
    void updateQuestionTest(){
        CompletableFuture<QuestionResponse> response = questionService.updateQuestionResponse(updateQuestionRequest);
        QuestionResponse jointResponse =  response.join();
        assertThat(response).isNotNull();
        assertEquals(jointResponse.getMessage(), "Question successfully updated.");
    }

    @Test
    void deleteQuestionTest(){
        CompletableFuture<QuestionResponse> response = questionService.deleteQuestionResponse(2L);
        QuestionResponse jointResponse =  response.join();
        assertThat(response).isNotNull();
        assertEquals(jointResponse.getMessage(),"Question has been successfully deleted.");
    }


}
