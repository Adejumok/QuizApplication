package com.africa.quizapp.web;

import com.africa.quizapp.dto.requests.AddQuizRequest;
import com.africa.quizapp.dto.responses.QuizResponse;
import com.africa.quizapp.exception.QuizApplicationException;
import com.africa.quizapp.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@Component
public class QuizAppHandler {
    private final QuizService quizService;

    public ServerResponse addQuiz(ServerRequest request){
        try {
            AddQuizRequest addQuizRequest = request.body(AddQuizRequest.class);
            CompletableFuture<QuizResponse> response = quizService.addQuizResponse(addQuizRequest);
            URI redirectUri = new URI("quiz.uri");
            return ServerResponse.created(redirectUri).body(response.join());
        }catch (Exception e){
            throw new QuizApplicationException("Quiz not added because -> "+e.getMessage());
        }
    }
}
