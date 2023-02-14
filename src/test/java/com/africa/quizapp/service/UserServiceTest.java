package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.MailRequest;
import com.africa.quizapp.dto.requests.RegisterUserRequest;
import com.africa.quizapp.dto.requests.UserRequestToTakeQuiz;
import com.africa.quizapp.dto.responses.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Slf4j
public class UserServiceTest {
    @Autowired
    private UserService userService;
    private RegisterUserRequest registerUserRequest;
    private UserRequestToTakeQuiz userRequestToTakeQuiz;

    @BeforeEach
    void setUp(){
        registerUserRequest = RegisterUserRequest.builder()
                .firstName("Kunle")
                .lastName("Particular")
                .email("kpart@gmail.com")
                .mobileNumber("090224673444")
                .address("No 13, Emily Akinola, Akoka")
                .build();

        userRequestToTakeQuiz = UserRequestToTakeQuiz.builder()
                .quizName("Human Analytics")
                .userEmail("kpart@gmail.com")
                .build();
    }

    @Test
    void registerUserTest(){
        UserResponse response = userService.registerUserResponse(registerUserRequest);
        log.info("{}", response.getMessage());
        assertThat(response).isNotNull();
        assertThat(response.getUser().getFirstName()).isEqualTo("Kunle");
    }

    @Test
    void subscribedUserCanTakeAQuiz(){
        UserResponse response = userService.subscribedUserTakesQuiz(userRequestToTakeQuiz);
        log.info("{}", response.getFormResponse());
        log.info("{}", response.getMessage());
        assertThat(response).isNotNull();
    }

    @Test
    void unSubscribedUserCanTakeAQuiz(){
        UserResponse response = userService.unsubscribedUserTakesQuiz(userRequestToTakeQuiz);
        log.info("{}", response.getFormResponse());
        log.info("{}", response.getMessage());
        assertThat(response).isNotNull();
    }


    @Test
    void deleteUserTest(){
        UserResponse response = userService.deleteUserResponse(1L);
        log.info("{}", response.getMessage());
        assertThat(response).isNotNull();
    }
}
