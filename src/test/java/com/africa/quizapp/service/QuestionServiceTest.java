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
                .text("How would you describe a set?")
                .category(Category.ADVANCED)
                .build();

        updateQuestionRequest = UpdateQuestionRequest.builder()
                .id(1L)
                .text("What is a noun?")
                .category(Category.INTERMEDIATE)
                .build();
    }

    @Test
    void addQuestionTest(){
        QuestionResponse response = questionService.addQuestionResponse(request);
        log.info("{}", response.getMessage());
        assertThat(response).isNotNull();
    }

    @Test
    void addAnswerToQuestionTest() {
        QuestionResponse response = questionService.addAnswerToQuestionResponse(1L, 1L);
        log.info("{}", response.getMessage());
        assertThat(response).isNotNull();
    }

    @Test
    void addCorrectAnswerTest(){
        QuestionResponse response = questionService.addCorrectAnswerResponse(1L, 1L);
        log.info("{}", response.getMessage());
        assertThat(response).isNotNull();
    }

    @Test
    void checkForCorrectAnswerTest(){
        QuestionResponse response = questionService.checkCorrectAnswerResponse(1L, 1L);
        log.info("{}", response.getMessage());
        assertThat(response).isNotNull();
    }

    @Test
    void checkForWrongAnswerTest(){
        QuestionResponse response = questionService.checkCorrectAnswerResponse(1L, 2L);
        log.info("{}", response.getMessage());
        assertThat(response).isNotNull();
    }

    @Test
    void updateQuestionTest(){
        QuestionResponse response = questionService.updateQuestionResponse(updateQuestionRequest);
        log.info("{}", response.getMessage());
        assertThat(response).isNotNull();
    }

    @Test
    void deleteQuestionTest(){
        QuestionResponse response = questionService.deleteQuestionResponse(4L);
        log.info("{}", response.getMessage());
        assertThat(response).isNotNull();
    }


}
