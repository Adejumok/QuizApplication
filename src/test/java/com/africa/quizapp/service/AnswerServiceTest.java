package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.AddAnswerRequest;
import com.africa.quizapp.dto.requests.UpdateAnswerRequest;
import com.africa.quizapp.dto.responses.AnswerResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
                .text("A collection of objects.")
                .build();

        updateRequest = UpdateAnswerRequest.builder()
                .id(1L)
                .text("Name")
                .build();
    }

    @Test
    void addAnswerRequestTest(){
        AnswerResponse response = answerService.addAnswerResponse(request);
        log.info("{}", response.getMessage());
        assertThat(response).isNotNull();
    }

    @Test
    void updateAnswerTest(){
        AnswerResponse response = answerService.updateAnswerResponse(updateRequest);
        log.info("{}", response.getMessage());
        assertThat(response).isNotNull();
    }

    @Test
    void deleteAnswerTest(){
        AnswerResponse response = answerService.deleteAnswerResponse(1L);
        log.info("{}", response.getMessage());
        assertThat(response).isNotNull();
    }
}
