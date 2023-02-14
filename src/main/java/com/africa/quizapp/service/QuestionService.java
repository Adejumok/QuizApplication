package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.AddQuestionRequest;
import com.africa.quizapp.dto.requests.UpdateQuestionRequest;
import com.africa.quizapp.dto.responses.QuestionResponse;
import com.africa.quizapp.models.quizModels.Question;

public interface QuestionService {
    QuestionResponse addQuestionResponse(AddQuestionRequest request);
    QuestionResponse addAnswerToQuestionResponse(long answerId, long questionId);
    QuestionResponse addCorrectAnswerResponse(Long questionId, Long answerId);

    QuestionResponse checkCorrectAnswerResponse(Long questionId, Long answerId);

    QuestionResponse updateQuestionResponse(UpdateQuestionRequest request);

    QuestionResponse deleteQuestionResponse(long id);

    Question locateAQuestion(Long id);

}
