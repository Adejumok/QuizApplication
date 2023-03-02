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
import java.util.concurrent.CompletionException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Slf4j
public class AnswerServiceTest {
    @Autowired
    private AnswerService answerService;
    private AddAnswerRequest request;
    private UpdateAnswerRequest updateRequest;

    @BeforeEach
    void setUp(){
        request = AddAnswerRequest.builder()
                .text("Make")
                .build();

        updateRequest = UpdateAnswerRequest.builder()
                .id(7L)
                .text("A name")
                .build();
    }

    @Test
    void addAnswerRequestTest(){
        CompletableFuture<AnswerResponse> response = answerService.addAnswerResponse(request);
        AnswerResponse obj =  response.join();
        assertThat(response).isNotNull();
        assertEquals(obj.getMessage(), "Answer successfully added.");
    }

    @Test
    void addSameAnswerRequestGetErrorTest(){
        CompletableFuture<AnswerResponse> response = answerService.addAnswerResponse(request);
        assertThat(response).isNotNull();
        assertThrows(CompletionException.class, response::join);
    }

    @Test
    void updateAnswerTest(){
        CompletableFuture<AnswerResponse> response = answerService.updateAnswerResponse(updateRequest);
        AnswerResponse jointResponse =  response.join();
        assertThat(response).isNotNull();
        assertEquals(jointResponse.getMessage(),"Answer successfully updated.");
    }

    @Test
    void deleteAnswerTest(){
        CompletableFuture<AnswerResponse> response = answerService.deleteAnswerResponse(6L);
        AnswerResponse jointResponse =  response.join();
        assertThat(response).isNotNull();
        assertEquals(jointResponse.getMessage(),"Answer has been successfully deleted.");
    }
}
