package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.AddQuizRequest;
import com.africa.quizapp.dto.requests.UpdateQuizRequest;
import com.africa.quizapp.dto.responses.QuizResponse;
import com.africa.quizapp.models.quizModels.Quiz;

import java.util.concurrent.CompletableFuture;

public interface QuizService {
    CompletableFuture<QuizResponse> addQuizResponse(AddQuizRequest request);

    CompletableFuture<QuizResponse> updateQuizResponse(UpdateQuizRequest updateRequest);

    CompletableFuture<QuizResponse> deleteQuizResponse(long id);

    CompletableFuture<QuizResponse> addQuestionToQuizResponse(long quizId, long questionId);
    Quiz getAQuizById(long id);
    Quiz getAQuizByName(String name);
}
