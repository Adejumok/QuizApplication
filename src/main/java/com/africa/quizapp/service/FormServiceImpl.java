package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.FormRequest;
import com.africa.quizapp.dto.requests.MailRequest;
import com.africa.quizapp.dto.responses.FormResponse;
import com.africa.quizapp.exception.QuizApplicationException;
import com.africa.quizapp.models.QuizUser;
import com.africa.quizapp.models.contactModels.Form;
import com.africa.quizapp.repository.FormRepository;
import com.africa.quizapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
@RequiredArgsConstructor
public class FormServiceImpl implements FormService{
    private final FormRepository repository;
    private final UserRepository userRepository;
    private final MailService mailService;
    private final Executor executor;

    @Override
    public CompletableFuture<FormResponse> formResponse(FormRequest formRequest) {
        QuizUser foundUser = userRepository.findByEmail(formRequest.getUserEmail()).orElse(null);
        return getFormResponse(foundUser);
    }

    private CompletableFuture<FormResponse> getFormResponse(QuizUser foundUser) {
        try {
            return CompletableFuture.supplyAsync(()->{
                Form form = new Form();
                form.setQuizUser(foundUser);
                form.setUserEmail(foundUser.getEmail());
                sendMail(foundUser);
                repository.save(form);
                return FormResponse.builder()
                        .message("User successfully added to form.")
                        .build();
            }, executor);
        }catch (Exception e){
            throw new QuizApplicationException("Form not added because -> "+e.getMessage());
        }

    }


    @Override
    public Form locateAForm(Long id){
        try {
            Optional<Form> foundForm = repository.findById(id);
            if (foundForm.isEmpty()){
                throw new QuizApplicationException("Form with id '"+id+"' does not exist.");
            }
            return foundForm.get();
        }catch (Exception e){
            throw new QuizApplicationException("Form not found because -> "+e.getMessage());
        }

    }

    private void sendMail(QuizUser quizUser){
        try {
            MailRequest mailRequest = MailRequest.builder()
                    .recipient(quizUser.getEmail())
                    .msgBody(""+quizUser.getReport())
                    .subject("Report of the Quiz")
                    .build();
            mailService.sendSimpleMail(mailRequest);
        }catch (Exception e){
            throw new QuizApplicationException("Mail not sent as a result of -> "+e.getMessage());
        }

    }
}
