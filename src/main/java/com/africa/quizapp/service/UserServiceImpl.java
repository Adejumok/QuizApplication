package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.FormRequest;
import com.africa.quizapp.dto.requests.RegisterUserRequest;
import com.africa.quizapp.dto.requests.UserRequestToTakeQuiz;
import com.africa.quizapp.dto.responses.UserResponse;
import com.africa.quizapp.exception.QuizApplicationException;
import com.africa.quizapp.models.quizModels.Quiz;
import com.africa.quizapp.models.QuizUser;
import com.africa.quizapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository repository;
    private final QuizService quizService;
    private final FormService formService;
    private final Executor executor;
    @Override
    public CompletableFuture<UserResponse> registerUserResponse(RegisterUserRequest registerUserRequest) {
        try {
            return CompletableFuture.supplyAsync(()->{
                QuizUser foundUser = repository.findByEmail(registerUserRequest.getEmail()).orElse(null);
                if (foundUser != null){
                    throw new QuizApplicationException("User with email "+registerUserRequest.getEmail()+" already exist.");
                }
                QuizUser user = new QuizUser();
                BeanUtils.copyProperties(registerUserRequest, user);
                repository.save(user);
                return UserResponse.builder()
                        .message("User with the email "+user.getEmail()+" registered successfully!")
                        .user(user)
                        .build();
            }, executor);
        }catch (Exception e){
            throw new QuizApplicationException("");
        }

    }


    @Override
    public QuizUser getAUserById(Long id) {
        try {
            Optional<QuizUser> foundUser = repository.findById(id);
            if (foundUser.isEmpty()){
                throw new QuizApplicationException("User with id '"+id+"' does not exist.");
            }
            return foundUser.get();
        }catch (Exception e){
            throw new QuizApplicationException("");
        }

    }

    @Override
    public CompletableFuture<UserResponse> subscribedUserTakesQuiz(UserRequestToTakeQuiz userRequestToTakeQuiz) {
        try {
            QuizUser quizUser = getAUserByEmail(userRequestToTakeQuiz.getUserEmail());
            Quiz foundQuiz = quizService.getAQuizByName(userRequestToTakeQuiz.getQuizName());
            quizUser.getQuizzes().add(foundQuiz);
            return getUserThatTakesQuizResponse(userRequestToTakeQuiz, quizUser);
        }catch (Exception e){
            throw new QuizApplicationException("");
        }
    }

    @Override
    public CompletableFuture<UserResponse> unsubscribedUserTakesQuiz(UserRequestToTakeQuiz userRequestToTakeQuiz) {
        return null;
    }


    private CompletableFuture<UserResponse> getUserThatTakesQuizResponse(UserRequestToTakeQuiz userRequestToTakeQuiz,
                                                      QuizUser quizUser) {
        try {
            return CompletableFuture.supplyAsync(()->{
                getFormRequest(quizUser);
                repository.save(quizUser);
                return UserResponse.builder()
                        .message("User with the email " + quizUser.getEmail() + " has taken quiz!")
                        .user(quizUser)
                        .build();
            }, executor);
        }catch (Exception e){
            throw new QuizApplicationException("");
        }

    }

    private FormRequest getFormRequest(QuizUser quizUser) {
        try {
            FormRequest formRequest = FormRequest.builder()
                    .userEmail(quizUser.getEmail())
                    .build();
            formService.formResponse(formRequest);
            return formRequest;
        }catch (Exception e){
            throw new QuizApplicationException("");
        }

    }

    @Override
    public QuizUser getAUserByEmail(String email) {
        try {
            Optional<QuizUser> foundUser = repository.findByEmail(email);
            if (foundUser.isEmpty()){
                throw new QuizApplicationException("User with email '"+email+"' does not exist.");
            }
            return foundUser.get();
        }catch (Exception e){
            throw new QuizApplicationException("");
        }
    }

    @Override
    public CompletableFuture<UserResponse> deleteUserResponse(long id) {
        try {
            return CompletableFuture.supplyAsync(()->{
                QuizUser foundUser = getAUserById(id);
                repository.delete(foundUser);
                return UserResponse.builder()
                        .message("User with id '"+id+"' has been successfully deleted.")
                        .build();
            }, executor);
        }catch (Exception e){
            throw new QuizApplicationException("");
        }

    }
}
