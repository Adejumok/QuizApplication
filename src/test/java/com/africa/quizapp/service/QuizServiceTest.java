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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
                .name("Zoolican")
                .build();

        updateRequest = UpdateQuizRequest.builder()
                .id(1L)
                .name("English Language")
                .build();
    }

    @Test
    void addQuizTest(){
        CompletableFuture<QuizResponse> response = quizService.addQuizResponse(request);
        log.info("{}", response);
        assertThat(response).isNotNull();
    }

    @Test
    void addQuestionToQuizTest() {
        CompletableFuture<QuizResponse> response = quizService.addQuestionToQuizResponse(102L, 1L);
        log.info("{}", response);
        assertThat(response).isNotNull();
    }
    
    @Test
    void updateQuizTest(){
        CompletableFuture<QuizResponse> response = quizService.updateQuizResponse(updateRequest);
        log.info("{}", response);
        assertThat(response).isNotNull(); 
    }

    @Test
    void deleteQuizTest(){
        CompletableFuture<QuizResponse> response = quizService.deleteQuizResponse(52L);
        log.info("{}", response);
        assertThat(response).isNotNull();
    }
}
