package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.AddAnswerRequest;
import com.africa.quizapp.dto.requests.UpdateAnswerRequest;
import com.africa.quizapp.dto.responses.AnswerResponse;
import com.africa.quizapp.models.quizModels.Answer;

import java.util.concurrent.CompletableFuture;

public interface AnswerService {
    CompletableFuture<AnswerResponse> addAnswerResponse(AddAnswerRequest request);

    CompletableFuture<AnswerResponse> updateAnswerResponse(UpdateAnswerRequest updateRequest);

    CompletableFuture<AnswerResponse>  deleteAnswerResponse(long id);
    Answer findAnAnswer(Long id);
}
