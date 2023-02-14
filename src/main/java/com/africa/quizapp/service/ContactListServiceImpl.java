package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.RegisterContactListRequest;
import com.africa.quizapp.dto.responses.ContactListResponse;
import com.africa.quizapp.dto.responses.QuestionResponse;
import com.africa.quizapp.exception.QuizApplicationException;
import com.africa.quizapp.models.contactModels.ContactList;
import com.africa.quizapp.models.contactModels.Form;
import com.africa.quizapp.models.quizModels.Answer;
import com.africa.quizapp.repository.ContactListRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ContactListServiceImpl implements ContactListService{
    private final ContactListRepository repository;
    private final FormService formService;

    @Override
    public ContactListResponse registerContactListResponse(RegisterContactListRequest contactListRequest) {
        ContactList foundContactList = repository.findById(contactListRequest.getContactListId()).orElse(null);
        if (foundContactList != null){
            throw new QuizApplicationException("Contact List with id "+contactListRequest.getContactListId()+" already exist.");
        }
        ContactList contactList = new ContactList();
        BeanUtils.copyProperties(contactListRequest, contactList);
        repository.save(contactList);
        return ContactListResponse.builder()
                .message("Contact List registered successfully.")
                .build();
    }

    @Override
    public ContactListResponse addFormToContactListResponse(Long contactListId, Long formId) {
        ContactList locatedContactList = getAContactList(contactListId);
        Form foundForm = formService.locateAForm(formId);
        locatedContactList.getForms().add(foundForm);
        repository.save(locatedContactList);
        return ContactListResponse.builder()
                .message("Form successfully added to Contact List with id '"+contactListId+"'!")
                .build();
    }

    @Override
    public ContactList getAContactList(Long id){
        Optional<ContactList> foundContactList = repository.findById(id);
        if (foundContactList.isEmpty()){
            throw new QuizApplicationException("Contact List with id '"+id+"' does not exist.");
        }
        return foundContactList.get();
    }
}
