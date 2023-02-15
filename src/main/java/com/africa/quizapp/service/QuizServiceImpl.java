package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.AddQuizRequest;
import com.africa.quizapp.dto.requests.UpdateQuizRequest;
import com.africa.quizapp.dto.responses.QuizResponse;
import com.africa.quizapp.exception.QuizApplicationException;
import com.africa.quizapp.models.quizModels.Question;
import com.africa.quizapp.models.quizModels.Quiz;
import com.africa.quizapp.repository.QuizRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService{
    private final QuizRepository repository;
    private QuestionService questionService;
    @Override
    public QuizResponse addQuizResponse(AddQuizRequest request) {
        Quiz foundQuiz = repository.findByName(request.getName()).orElse(null);
        if (foundQuiz != null){
            throw new QuizApplicationException("Quiz with title "+request.getName()+" already exist.");
        }
        Quiz quiz = new Quiz();
        BeanUtils.copyProperties(request, quiz);
        repository.save(quiz);
        return QuizResponse.builder()
                .message("Quiz with the name '"+request.getName()+"' created successfully!")
                .build();
    }

    @Override
    public QuizResponse updateQuizResponse(UpdateQuizRequest updateRequest) {
        Quiz foundQuiz = getAQuizById(updateRequest.getId());
        BeanUtils.copyProperties(updateRequest, foundQuiz);
        repository.save(foundQuiz);
        return QuizResponse.builder()
                .message("Quiz successfully updated.")
                .build();
    }

    @Override
    public QuizResponse deleteQuizResponse(long id) {
        Quiz foundQuiz = getAQuizById(id);
        repository.delete(foundQuiz);
        return QuizResponse.builder()
                .message("Quiz with id '"+id+"' has been successfully deleted.")
                .build();
    }

    @Override
    public QuizResponse addQuestionToQuizResponse(long quizId, long questionId) {
        Quiz foundQuiz = getAQuizById(quizId);
        Question locatedQuestion = questionService.locateAQuestion(questionId);
        foundQuiz.getQuestions().add(locatedQuestion);
        repository.save(foundQuiz);
        return QuizResponse.builder()
                .quizScore(foundQuiz.getGrade())
                .message("Question successfully added to Quiz with id '"+quizId+"'!")
                .build();
    }

    @Override
    public Quiz getAQuizById(long id) {
        Optional<Quiz> foundQuiz = repository.findById(id);
        if (foundQuiz.isEmpty()){
            throw new QuizApplicationException("Quiz with id '"+id+"' does not exist.");
        }
        return foundQuiz.get();
    }

    @Override
    public Quiz getAQuizByName(String name) {
        Optional<Quiz> foundQuiz = repository.findByName(name);
        if (foundQuiz.isEmpty()){
            throw new QuizApplicationException("Quiz with name '"+name+"' does not exist.");
        }
        return foundQuiz.get();
    }
}
