package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.FormRequest;
import com.africa.quizapp.dto.requests.RegisterUserRequest;
import com.africa.quizapp.dto.requests.UserRequestToTakeQuiz;
import com.africa.quizapp.dto.responses.UserResponse;
import com.africa.quizapp.models.QuizUser;

public interface UserService {
    UserResponse registerUserResponse(RegisterUserRequest registerUserRequest);
    UserResponse subscribedUserTakesQuiz(UserRequestToTakeQuiz userRequestToTakeQuiz);
    UserResponse unsubscribedUserTakesQuiz(UserRequestToTakeQuiz userRequestToTakeQuiz);
    UserResponse deleteUserResponse(long id);
    QuizUser getAUserById(Long id);
    QuizUser getAUserByEmail(String email);


}
