package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.RegisterUserRequest;
import com.africa.quizapp.dto.requests.UpdateUserRequest;
import com.africa.quizapp.dto.requests.UserRequestToTakeQuiz;
import com.africa.quizapp.dto.responses.QuestionResponse;
import com.africa.quizapp.dto.responses.UserResponse;
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
public class UserServiceTest {
    @Autowired
    private UserService userService;
    private RegisterUserRequest registerUserRequest;
    private UserRequestToTakeQuiz userRequestToTakeQuiz;
    private UserRequestToTakeQuiz unsubscribedUserRequestToTakeQuiz;
    private UpdateUserRequest updateUserRequest;

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
                .quizName("Bob Daddy")
                .userEmail("lolo@gmail.com")
                .build();
        unsubscribedUserRequestToTakeQuiz = UserRequestToTakeQuiz.builder()
                .quizName("Bob Daddy")
                .userEmail("funmi@gmail.com")
                .build();
        updateUserRequest = UpdateUserRequest.builder()
                .id(1L)
                .firstName("Solo")
                .lastName("Makinde")
                .email("solo@gmail.com")
                .address("No 10, Emily Akinola, Akoka")
                .mobileNumber("09023323235")
                .build();
    }

    @Test
    void registerUserTest(){
        CompletableFuture<UserResponse> response = userService.registerUserResponse(registerUserRequest);
        UserResponse jointResponse =  response.join();
        assertThat(response).isNotNull();
        assertEquals(jointResponse.getMessage(), "User with the email "+registerUserRequest.getEmail()+" registered successfully!");
    }

    @Test
    void registerSameUserRequestGetErrorTest(){
        CompletableFuture<UserResponse> response = userService.registerUserResponse(registerUserRequest);
        assertThat(response).isNotNull();
        assertThrows(CompletionException.class, response::join);
    }

    @Test
    void subscribedUserCanTakeAQuiz(){
        CompletableFuture<UserResponse> response = userService.subscribedUserTakesQuiz(userRequestToTakeQuiz);
        UserResponse jointResponse =  response.join();
        assertThat(response).isNotNull();
        assertEquals(jointResponse.getMessage(), "User has taken quiz!");
    }

    @Test
    void unSubscribedUserCanTakeAQuiz(){
        CompletableFuture<UserResponse> response = userService.unsubscribedUserTakesQuiz(unsubscribedUserRequestToTakeQuiz);
        UserResponse jointResponse =  response.join();
        assertThat(response).isNotNull();
        assertEquals(jointResponse.getMessage(), "User has taken quiz!");
    }
    @Test
    void updateUserTest(){
        CompletableFuture<UserResponse> response = userService.updateUserResponse(updateUserRequest);
        UserResponse jointResponse =  response.join();
        assertThat(response).isNotNull();
        assertEquals(jointResponse.getMessage(), "User successfully updated.");
    }


    @Test
    void deleteUserTest(){
        CompletableFuture<UserResponse> response = userService.deleteUserResponse(1L);
        UserResponse jointResponse =  response.join();
        assertThat(response).isNotNull();
        assertEquals(jointResponse.getMessage(), "User with id '"+1L+"' has been successfully deleted.");
    }
}
