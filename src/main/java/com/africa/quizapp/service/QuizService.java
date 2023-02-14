package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.AddQuizRequest;
import com.africa.quizapp.dto.requests.UpdateQuizRequest;
import com.africa.quizapp.dto.responses.QuizResponse;
import com.africa.quizapp.models.quizModels.Quiz;

public interface QuizService {
    QuizResponse addQuizResponse(AddQuizRequest request);

    QuizResponse updateQuizResponse(UpdateQuizRequest updateRequest);

    QuizResponse deleteQuizResponse(long id);

    QuizResponse addQuestionToQuizResponse(long quizId, long questionId);
    Quiz getAQuizById(long id);
    Quiz getAQuizByName(String name);
}
