package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.FormRequest;
import com.africa.quizapp.dto.requests.RegisterUserRequest;
import com.africa.quizapp.dto.requests.UpdateUserRequest;
import com.africa.quizapp.dto.requests.UserRequestToTakeQuiz;
import com.africa.quizapp.dto.responses.UserResponse;
import com.africa.quizapp.models.QuizUser;

import java.util.concurrent.CompletableFuture;

public interface UserService {
    CompletableFuture<UserResponse> registerUserResponse(RegisterUserRequest registerUserRequest);
    CompletableFuture<UserResponse> subscribedUserTakesQuiz(UserRequestToTakeQuiz userRequestToTakeQuiz);
    CompletableFuture<UserResponse> unsubscribedUserTakesQuiz(UserRequestToTakeQuiz userRequestToTakeQuiz);
    CompletableFuture<UserResponse> deleteUserResponse(long id);
    QuizUser getAUserById(Long id);
    QuizUser getAUserByEmail(String email);


    CompletableFuture<UserResponse> updateUserResponse(UpdateUserRequest updateUserRequest);
}
