package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.AddAnswerRequest;
import com.africa.quizapp.dto.requests.UpdateAnswerRequest;
import com.africa.quizapp.dto.responses.AnswerResponse;
import com.africa.quizapp.exception.QuizApplicationException;
import com.africa.quizapp.models.quizModels.Answer;
import com.africa.quizapp.repository.AnswerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository repository;
    @Override
    public AnswerResponse addAnswerResponse(AddAnswerRequest request) {
        Answer foundAnswer = repository.findByText(request.getText()).orElse(null);
        if (foundAnswer != null){
            throw new QuizApplicationException("Answer with title "+request.getText()+" already exist.");
        }
        Answer answer = new Answer();
        BeanUtils.copyProperties(request, answer);
        repository.save(answer);
        return AnswerResponse.builder()
                .message("Answer is successfully added.")
                .build();
    }

    @Override
    public AnswerResponse updateAnswerResponse(UpdateAnswerRequest updateRequest) {
        Answer foundAnswer = findAnAnswer(updateRequest.getId());
        BeanUtils.copyProperties(updateRequest, foundAnswer);
        repository.save(foundAnswer);
        return AnswerResponse.builder()
                .message("Answer successfully updated.")
                .build();
    }

    @Override
    public AnswerResponse deleteAnswerResponse(long id) {
        Answer foundAnswer = findAnAnswer(id);
        repository.delete(foundAnswer);
        return AnswerResponse.builder()
                .message("Answer has been successfully deleted.")
                .build();
    }
    @Override
    public Answer findAnAnswer(Long id){
        Optional<Answer> foundAnswer = repository.findById(id);
        if (foundAnswer.isEmpty()){
            throw new QuizApplicationException("Answer with id '"+id+"' does not exist.");
        }
        return foundAnswer.get();
    }
}
