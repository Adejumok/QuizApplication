package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.AddAnswerRequest;
import com.africa.quizapp.dto.requests.UpdateAnswerRequest;
import com.africa.quizapp.dto.responses.AnswerResponse;
import com.africa.quizapp.exception.QuizApplicationException;
import com.africa.quizapp.models.quizModels.Answer;
import com.africa.quizapp.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository repository;
    private final Executor executor;
    @Override
    public CompletableFuture<AnswerResponse> addAnswerResponse(AddAnswerRequest request) {
        try {
            return CompletableFuture.supplyAsync(() ->{
                Answer foundAnswer = repository.findByText(request.getText())
                        .orElse(null);
                if (foundAnswer != null){
                    throw new QuizApplicationException("Answer with text "+request.getText()+" already exist.");
                }
                Answer answer = new Answer();
                BeanUtils.copyProperties(request, answer);
                repository.save(answer);
                return AnswerResponse.builder()
                        .message("Answer successfully added.")
                        .build();
            }, executor);
        }catch (Exception e){
            throw new QuizApplicationException("Answer with text "+request.getText()+" not added.");
        }

    }

    @Override
    public CompletableFuture<AnswerResponse> updateAnswerResponse(UpdateAnswerRequest updateRequest) {
        try {
            return CompletableFuture.supplyAsync(() -> {
                            Answer foundAnswer = findAnAnswer(updateRequest.getId());
                            BeanUtils.copyProperties(updateRequest, foundAnswer);
                            repository.save(foundAnswer);
                        return AnswerResponse.builder()
                                .message("Answer successfully updated.")
                                .build();}, executor);
        }catch (Exception e){
            throw new QuizApplicationException("Answer not updated.");
        }
    }

    @Override
    public CompletableFuture<AnswerResponse>  deleteAnswerResponse(long id) {
        try {
            return CompletableFuture.supplyAsync(()->{
                Answer foundAnswer = findAnAnswer(id);
                repository.delete(foundAnswer);
                return AnswerResponse.builder()
                        .message("Answer has been successfully deleted.")
                        .build();
            }, executor);
        }catch (Exception e){
            throw new QuizApplicationException("");
        }
    }
    @Override
    public Answer findAnAnswer(Long id){
        try {
            Optional<Answer> foundAnswer = repository.findById(id);
            if (foundAnswer.isEmpty()){
                throw new QuizApplicationException("Answer with id '"+id+"' does not exist.");
            }
            return foundAnswer.get();
        }catch (Exception e){
            throw new QuizApplicationException("");
        }

    }
}
