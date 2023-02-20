package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.AddQuestionRequest;
import com.africa.quizapp.dto.requests.UpdateQuestionRequest;
import com.africa.quizapp.dto.responses.QuestionResponse;
import com.africa.quizapp.models.quizModels.Question;

import java.util.concurrent.CompletableFuture;

public interface QuestionService {
    CompletableFuture<QuestionResponse> addQuestionResponse(AddQuestionRequest request);
    CompletableFuture<QuestionResponse> addAnswerToQuestionResponse(long answerId, long questionId);
    CompletableFuture<QuestionResponse> addCorrectAnswerResponse(Long questionId, Long answerId);

    CompletableFuture<QuestionResponse> checkCorrectAnswerResponse(Long questionId, Long answerId);

    CompletableFuture<QuestionResponse> updateQuestionResponse(UpdateQuestionRequest request);

    CompletableFuture<QuestionResponse> deleteQuestionResponse(long id);

    Question locateAQuestion(Long id);

}
