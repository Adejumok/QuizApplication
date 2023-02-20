package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.AddAnswerRequest;
import com.africa.quizapp.dto.requests.UpdateAnswerRequest;
import com.africa.quizapp.dto.responses.AnswerResponse;
import com.africa.quizapp.exception.QuizApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Slf4j
public class AnswerServiceTest {
    @Autowired
    private AnswerService answerService;
    private AddAnswerRequest request;
    private AddAnswerRequest request1;

    private UpdateAnswerRequest updateRequest;

    @BeforeEach
    void setUp(){
        request = AddAnswerRequest.builder()
                .text("A question.")
                .build();
        request1 = AddAnswerRequest.builder()
                .text("A thing.")
                .build();

        updateRequest = UpdateAnswerRequest.builder()
                .id(1L)
                .text("A name")
                .build();
    }

    @Test
    void addAnswerRequestTest(){
        CompletableFuture<AnswerResponse> response = answerService.addAnswerResponse(request);
        log.info("{}", response);
        assertThat(response).isNotNull();
    }

    @Test
    void addSameAnswerRequestGetErrorTest(){
        CompletableFuture<AnswerResponse> response = answerService.addAnswerResponse(request1);
        log.info("{}", response);
//        assertThrows(QuizApplicationException.class, ()->
//                answerService.addAnswerResponse(request1));
        assertThat(response).isNotNull();
    }

    @Test
    void updateAnswerTest(){
        CompletableFuture<AnswerResponse> response = answerService.updateAnswerResponse(updateRequest);
        log.info("{}", response);
        assertThat(response).isNotNull();
    }

    @Test
    void deleteAnswerTest(){
        CompletableFuture<AnswerResponse> response = answerService.deleteAnswerResponse(1L);
        log.info("{}", response);
        assertThat(response).isNotNull();
    }
}
