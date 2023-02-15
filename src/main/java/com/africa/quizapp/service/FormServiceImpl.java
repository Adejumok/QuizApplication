package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.FormRequest;
import com.africa.quizapp.dto.requests.MailRequest;
import com.africa.quizapp.dto.responses.FormResponse;
import com.africa.quizapp.exception.QuizApplicationException;
import com.africa.quizapp.models.QuizUser;
import com.africa.quizapp.models.contactModels.Form;
import com.africa.quizapp.repository.FormRepository;
import com.africa.quizapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FormServiceImpl implements FormService{
    private final FormRepository repository;
    private final UserRepository userRepository;
    private final MailService mailService;
    @Override
    public FormResponse formResponse(FormRequest formRequest) {
        QuizUser foundUser = userRepository.findByEmail(formRequest.getUserEmail()).orElse(null);
        return getFormResponse(foundUser);
    }

    private FormResponse getFormResponse(QuizUser foundUser) {
        Form form = new Form();
        form.setQuizUser(foundUser);
        form.setUserEmail(foundUser.getEmail());
        sendMail(foundUser);
        repository.save(form);
        return FormResponse.builder()
                .message("User successfully added to form.")
                .build();
    }


    @Override
    public Form locateAForm(Long id){
        Optional<Form> foundForm = repository.findById(id);
        if (foundForm.isEmpty()){
            throw new QuizApplicationException("Form with id '"+id+"' does not exist.");
        }
        return foundForm.get();
    }

    private void sendMail(QuizUser quizUser){
        MailRequest mailRequest = MailRequest.builder()
                .recipient(quizUser.getEmail())
                .msgBody(""+quizUser.getReport())
                .subject("Report of the Quiz")
                .build();
        mailService.sendSimpleMail(mailRequest);
    }
}
