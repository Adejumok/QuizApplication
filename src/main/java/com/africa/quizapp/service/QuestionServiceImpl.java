package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.AddQuestionRequest;
import com.africa.quizapp.dto.requests.UpdateQuestionRequest;
import com.africa.quizapp.dto.responses.QuestionResponse;
import com.africa.quizapp.exception.QuizApplicationException;
import com.africa.quizapp.models.quizModels.Answer;
import com.africa.quizapp.models.quizModels.Question;
import com.africa.quizapp.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
class QuestionServiceImpl implements QuestionService{
    private final QuestionRepository repository;
    private final AnswerService answerService;
    @Override
    public QuestionResponse addQuestionResponse(AddQuestionRequest request) {
        Question foundQuestion = repository.findByText(request.getText()).orElse(null);
        if (foundQuestion != null){
            throw new QuizApplicationException("Question with title "+request.getText()+" already exist.");
        }
        Question question = new Question();
        BeanUtils.copyProperties(request, question);
        repository.save(question);
        return QuestionResponse.builder()
                .message("Question created successfully.")
                .build();
    }

    @Override
    public QuestionResponse addAnswerToQuestionResponse(long answerId, long questionId) {
        Question locatedQuestion = locateAQuestion(questionId);
        Answer foundAnswer = answerService.findAnAnswer(answerId);
        locatedQuestion.getAnswers().add(foundAnswer);
        repository.save(locatedQuestion);
        return QuestionResponse.builder()
                .message("Answer successfully added to Question with id '"+questionId+"'!")
                .build();
    }

    @Override
    public QuestionResponse addCorrectAnswerResponse(Long questionId, Long answerId) {
        Question locatedQuestion = locateAQuestion(questionId);
        Answer foundAnswer = answerService.findAnAnswer(answerId);
        locatedQuestion.setCorrectAnswer(foundAnswer);
        repository.save(locatedQuestion);
        return QuestionResponse.builder()
                .message("Answer with id '"+answerId+"' successfully " +
                        "added to this Question as the correct answer!")
                .build();
    }

    @Override
    public QuestionResponse checkCorrectAnswerResponse(Long questionId, Long answerId) {
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
    }
    @Override
    public Question locateAQuestion(Long id){
        Optional<Question> foundQuestion = repository.findById(id);
        if (foundQuestion.isEmpty()){
            throw new QuizApplicationException("Question with id '"+id+"' does not exist.");
        }
        return foundQuestion.get();
    }

    @Override
    public QuestionResponse updateQuestionResponse(UpdateQuestionRequest request) {
        Question locatedQuestion = locateAQuestion(request.getId());
        BeanUtils.copyProperties(request, locatedQuestion);
        repository.save(locatedQuestion);
        return QuestionResponse.builder()
                .message("Question successfully updated.")
                .build();
    }

    @Override
    public QuestionResponse deleteQuestionResponse(long id) {
        Question locatedQuestion = locateAQuestion(id);
        repository.delete(locatedQuestion);
        return QuestionResponse.builder()
                .message("Question has been successfully deleted.")
                .build();
    }

}
