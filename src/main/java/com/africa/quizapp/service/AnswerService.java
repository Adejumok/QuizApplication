package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.AddAnswerRequest;
import com.africa.quizapp.dto.requests.UpdateAnswerRequest;
import com.africa.quizapp.dto.responses.AnswerResponse;
import com.africa.quizapp.models.quizModels.Answer;

public interface AnswerService {
    AnswerResponse addAnswerResponse(AddAnswerRequest request);

    AnswerResponse updateAnswerResponse(UpdateAnswerRequest updateRequest);

    AnswerResponse deleteAnswerResponse(long id);
    Answer findAnAnswer(Long id);
}
