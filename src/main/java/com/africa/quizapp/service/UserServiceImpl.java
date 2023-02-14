package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.FormRequest;
import com.africa.quizapp.dto.requests.RegisterUserRequest;
import com.africa.quizapp.dto.requests.UserRequestToTakeQuiz;
import com.africa.quizapp.dto.responses.UserResponse;
import com.africa.quizapp.exception.QuizApplicationException;
import com.africa.quizapp.models.quizModels.Quiz;
import com.africa.quizapp.models.QuizUser;
import com.africa.quizapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository repository;
    private final QuizService quizService;
    private final FormService formService;
    @Override
    public UserResponse registerUserResponse(RegisterUserRequest registerUserRequest) {
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
    }


    @Override
    public QuizUser getAUserById(Long id) {
        Optional<QuizUser> foundUser = repository.findById(id);
        if (foundUser.isEmpty()){
            throw new QuizApplicationException("User with id '"+id+"' does not exist.");
        }
        return foundUser.get();
    }

    @Override
    public UserResponse subscribedUserTakesQuiz(UserRequestToTakeQuiz userRequestToTakeQuiz) {
        QuizUser quizUser = getAUserByEmail(userRequestToTakeQuiz.getUserEmail());
        return getUserThatTakesQuizResponse(userRequestToTakeQuiz, quizUser);
    }

    @Override
    public UserResponse unsubscribedUserTakesQuiz(UserRequestToTakeQuiz userRequestToTakeQuiz) {
        QuizUser quizUser = new QuizUser();
        return getUserThatTakesQuizResponse(userRequestToTakeQuiz, quizUser);
    }

    private UserResponse getUserThatTakesQuizResponse(UserRequestToTakeQuiz userRequestToTakeQuiz, QuizUser quizUser) {
        Quiz foundQuiz = quizService.getAQuizByName(userRequestToTakeQuiz.getQuizName());
        quizUser.getQuizzes().add(foundQuiz);
        repository.save(quizUser);
        FormRequest formRequest = getFormRequest(quizUser);
        return UserResponse.builder()
                .message("User with the email " + quizUser.getEmail() + " has taken quiz!")
                .formResponse(formService.formResponse(formRequest))
                .user(quizUser)
                .build();
    }

    private FormRequest getFormRequest(QuizUser quizUser) {
        FormRequest formRequest = FormRequest.builder()
                .userEmail(quizUser.getEmail())
                .build();
        formService.formResponse(formRequest);
        return formRequest;
    }

    @Override
    public QuizUser getAUserByEmail(String email) {
        Optional<QuizUser> foundUser = repository.findByEmail(email);
        if (foundUser.isEmpty()){
            throw new QuizApplicationException("User with email '"+email+"' does not exist.");
        }
        return foundUser.get();
    }

    @Override
    public UserResponse deleteUserResponse(long id) {
        QuizUser foundUser = getAUserById(id);
        repository.delete(foundUser);
        return UserResponse.builder()
                .message("User with id '"+id+"' has been successfully deleted.")
                .build();
    }
}
