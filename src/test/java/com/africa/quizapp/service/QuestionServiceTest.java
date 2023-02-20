package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.AddQuestionRequest;
import com.africa.quizapp.dto.requests.UpdateQuestionRequest;
import com.africa.quizapp.dto.responses.QuestionResponse;
import com.africa.quizapp.models.enums.Category;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@Slf4j
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
                .text("How would you describe a marketing?")
                .category(Category.INTERMEDIATE)
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
        log.info("{}", response);
        assertThat(response).isNotNull();
    }

    @Test
    void addAnswerToQuestionTest() throws ExecutionException, InterruptedException {
        CompletableFuture<QuestionResponse> response = questionService.addAnswerToQuestionResponse(1L, 1L);
        log.info("{}", response.get());
        assertThat(response).isNotNull();
    }

    @Test
    void addCorrectAnswerTest(){
        CompletableFuture<QuestionResponse> response = questionService.addCorrectAnswerResponse(1L, 1L);
        log.info("{}", response);
        assertThat(response).isNotNull();
    }

    @Test
    void checkForCorrectAnswerTest(){
        CompletableFuture<QuestionResponse> response = questionService.checkCorrectAnswerResponse(1L, 1L);
        log.info("{}", response);
        assertThat(response).isNotNull();
    }

    @Test
    void checkForWrongAnswerTest(){
        CompletableFuture<QuestionResponse> response = questionService.checkCorrectAnswerResponse(1L, 2L);
        log.info("{}", response);
        assertThat(response).isNotNull();
    }

    @Test
    void updateQuestionTest(){
        CompletableFuture<QuestionResponse> response = questionService.updateQuestionResponse(updateQuestionRequest);
        log.info("{}", response);
        assertThat(response).isNotNull();
    }

    @Test
    void deleteQuestionTest(){
        CompletableFuture<QuestionResponse> response = questionService.deleteQuestionResponse(4L);
        log.info("{}", response);
        assertThat(response).isNotNull();
    }


}
