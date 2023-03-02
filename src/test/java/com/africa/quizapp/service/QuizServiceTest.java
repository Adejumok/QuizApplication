package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.AddQuizRequest;
import com.africa.quizapp.dto.requests.UpdateQuizRequest;
import com.africa.quizapp.dto.responses.QuizResponse;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class QuizServiceTest {
    @Autowired
    private QuizService quizService;
    private AddQuizRequest request;
    private UpdateQuizRequest updateRequest;

    @BeforeEach
    void setUp(){
        request = AddQuizRequest.builder()
                .name("Bob Daddy")
                .build();

        updateRequest = UpdateQuizRequest.builder()
                .id(52L)
                .name("English Language")
                .build();
    }

    @Test
    void addQuizTest(){
        CompletableFuture<QuizResponse> response = quizService.addQuizResponse(request);
        QuizResponse jointResponse =  response.join();
        assertThat(response).isNotNull();
        assertEquals(jointResponse.getMessage(), "Quiz with the name '"+request.getName()+"' created successfully!");
    }

    @Test
    void addSameQuizRequestGetErrorTest(){
        CompletableFuture<QuizResponse> response = quizService.addQuizResponse(request);
        assertThat(response).isNotNull();
        assertThrows(CompletionException.class, response::join);
    }

    @Test
    void addQuestionToQuizTest() {
        CompletableFuture<QuizResponse> response = quizService.addQuestionToQuizResponse(52L, 3L);
        QuizResponse jointResponse =  response.join();
        assertThat(response).isNotNull();
        assertEquals(jointResponse.getMessage(), "Question successfully added to Quiz with id '"+52L+"'!");
    }
    
    @Test
    void updateQuizTest(){
        CompletableFuture<QuizResponse> response = quizService.updateQuizResponse(updateRequest);
        QuizResponse jointResponse =  response.join();
        assertThat(response).isNotNull();
        assertEquals(jointResponse.getMessage(), "Quiz successfully updated.");
    }

    @Test
    void deleteQuizTest(){
        CompletableFuture<QuizResponse> response = quizService.deleteQuizResponse(52L);
        QuizResponse jointResponse =  response.join();
        assertThat(response).isNotNull();
        assertEquals(jointResponse.getMessage(), "Quiz with id '"+52L+"' has been successfully deleted.");
    }
}
