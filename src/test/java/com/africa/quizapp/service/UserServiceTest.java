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

import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Slf4j
public class UserServiceTest {
    @Autowired
    private UserService userService;
    private RegisterUserRequest registerUserRequest;
    private UserRequestToTakeQuiz userRequestToTakeQuiz;
    private UserRequestToTakeQuiz unsubscribedUserRequestToTakeQuiz;

    @BeforeEach
    void setUp(){
        registerUserRequest = RegisterUserRequest.builder()
                .firstName("Lolo")
                .lastName("Okocha")
                .email("lolo@gmail.com")
                .mobileNumber("0901013342")
                .address("No 5, New Road, Sabo")
                .build();

        userRequestToTakeQuiz = UserRequestToTakeQuiz.builder()
                .quizName("Zoolican")
                .userEmail("lolo@gmail.com")
                .build();
        unsubscribedUserRequestToTakeQuiz = UserRequestToTakeQuiz.builder()
                .quizName("Nectonomy")
                .userEmail("funmi@gmail.com")
                .build();
    }

    @Test
    void registerUserTest(){
        CompletableFuture<UserResponse> response = userService.registerUserResponse(registerUserRequest);
        log.info("{}", response);
        assertThat(response).isNotNull();
    }

    @Test
    void subscribedUserCanTakeAQuiz(){
        CompletableFuture<UserResponse> response = userService.subscribedUserTakesQuiz(userRequestToTakeQuiz);
        log.info("{}", response);
        assertThat(response).isNotNull();
    }


    @Test
    void deleteUserTest(){
        CompletableFuture<UserResponse> response = userService.deleteUserResponse(1L);
        log.info("{}", response);
        assertThat(response).isNotNull();
    }
}
