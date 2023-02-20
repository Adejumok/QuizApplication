package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.AddQuestionRequest;
import com.africa.quizapp.dto.requests.UpdateQuestionRequest;
import com.africa.quizapp.dto.responses.QuestionResponse;
import com.africa.quizapp.exception.QuizApplicationException;
import com.africa.quizapp.models.quizModels.Answer;
import com.africa.quizapp.models.quizModels.Question;
import com.africa.quizapp.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RequiredArgsConstructor
@Service
class QuestionServiceImpl implements QuestionService{
    private final QuestionRepository repository;
    private final AnswerService answerService;
    private final Executor executor;

    @Override
    public CompletableFuture<QuestionResponse> addQuestionResponse(AddQuestionRequest request) {
        try {
            return CompletableFuture.supplyAsync(()->{
                Question question = new Question();
                BeanUtils.copyProperties(request, question);
                repository.save(question);
                return QuestionResponse.builder()
                        .message("Question created successfully.")
                        .build();
            }, executor);
        }catch (Exception e){
            throw new QuizApplicationException("Question with title "+request.getText()+" not added.");
        }


    }

    @Override
    public CompletableFuture<QuestionResponse> addAnswerToQuestionResponse(long answerId, long questionId) {
        try {
            return CompletableFuture.supplyAsync(()->{
                Question locatedQuestion = locateAQuestion(questionId);
                Answer foundAnswer = answerService.findAnAnswer(answerId);
                locatedQuestion.getAnswers().add(foundAnswer);
                repository.save(locatedQuestion);
                return QuestionResponse.builder()
                        .message("Answer successfully added to Question with id '"+questionId+"'!")
                        .build();
            }, executor);
        }catch (Exception e){
            throw new QuizApplicationException("Answer with id "+answerId+" not added to the Question.");
        }

    }

    @Override
    public CompletableFuture<QuestionResponse> addCorrectAnswerResponse(Long questionId, Long answerId) {
        try {
            return CompletableFuture.supplyAsync(()->{
                Question locatedQuestion = locateAQuestion(questionId);
                Answer foundAnswer = answerService.findAnAnswer(answerId);
                locatedQuestion.setCorrectAnswer(foundAnswer);
                repository.save(locatedQuestion);
                return QuestionResponse.builder()
                        .message("Answer with id '"+answerId+"' successfully " +
                                "added to this Question as the correct answer!")
                        .build();
            }, executor);
        }catch (Exception e){
            throw new QuizApplicationException("Correct Answer with id "+answerId+" not added to the Question.");
        }

    }

    @Override
    public CompletableFuture<QuestionResponse> checkCorrectAnswerResponse(Long questionId, Long answerId) {
        try {
            return CompletableFuture.supplyAsync(()->{
                Question locatedQuestion = locateAQuestion(questionId);
                Answer foundAnswer = answerService.findAnAnswer(answerId);
                if(foundAnswer != null && foundAnswer.getText()
                        .equalsIgnoreCase(locatedQuestion.getCorrectAnswer().getText())){
                    repository.save(locatedQuestion);
                    return QuestionResponse.builder()
                            .message("Correct!")
                            .build();
                }
                return QuestionResponse.builder()
                        .message("Oops! Wrong.")
                        .build();
            }, executor);
        }catch (Exception e){
            throw new QuizApplicationException("Answer with id "+answerId+" not checked.");
        }

    }
    @Override
    public Question locateAQuestion(Long id){
        try {
            Optional<Question> foundQuestion = repository.findById(id);
            if (foundQuestion.isEmpty()){
                throw new QuizApplicationException("Question with id '"+id+"' does not exist.");
            }
            return foundQuestion.get();
        }catch (Exception e){
            throw new QuizApplicationException("");
        }

    }

    @Override
    public CompletableFuture<QuestionResponse> updateQuestionResponse(UpdateQuestionRequest request) {
        try{
            return CompletableFuture.supplyAsync(()->{
                Question locatedQuestion = locateAQuestion(request.getId());
                BeanUtils.copyProperties(request, locatedQuestion);
                repository.save(locatedQuestion);
                return QuestionResponse.builder()
                        .message("Question successfully updated.")
                        .build();
            }, executor);
        }catch (Exception e){
            throw new QuizApplicationException("Question with id "+request.getId()+" not updated.");
        }

    }

    @Override
    public CompletableFuture<QuestionResponse> deleteQuestionResponse(long id) {
        try{
            return CompletableFuture.supplyAsync(()->{
                Question locatedQuestion = locateAQuestion(id);
                repository.delete(locatedQuestion);
                return QuestionResponse.builder()
                        .message("Question has been successfully deleted.")
                        .build();
            }, executor);
        }catch (Exception e){
            throw new QuizApplicationException("Question with id "+id+" not deleted.");
        }

    }

}
