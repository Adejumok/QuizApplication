package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.RegisterContactListRequest;
import com.africa.quizapp.dto.responses.ContactListResponse;
import com.africa.quizapp.exception.QuizApplicationException;
import com.africa.quizapp.models.contactModels.ContactList;
import com.africa.quizapp.models.contactModels.Form;
import com.africa.quizapp.repository.ContactListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
@RequiredArgsConstructor
public class ContactListServiceImpl implements ContactListService{
    private final ContactListRepository repository;
    private final FormService formService;
    private final Executor executor;


    @Override
    public CompletableFuture<ContactListResponse> registerContactListResponse(RegisterContactListRequest contactListRequest) {
        try {
            return CompletableFuture.supplyAsync(()->{
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
            }, executor);
        }catch (Exception e){
            throw new QuizApplicationException("Contact List not registered because -> "+e.getMessage());
        }

    }

    @Override
    public CompletableFuture<ContactListResponse> addFormToContactListResponse(Long contactListId, Long formId) {
        try {
            return CompletableFuture.supplyAsync(()->{
                ContactList locatedContactList = getAContactList(contactListId);
                Form foundForm = formService.locateAForm(formId);
                locatedContactList.getForms().add(foundForm);
                repository.save(locatedContactList);
                return ContactListResponse.builder()
                        .message("Form successfully added to Contact List with id '"+contactListId+"'!")
                        .build();
            }, executor);
        }catch (Exception e){
            throw new QuizApplicationException("Form not added to Contact List due to -> "+e.getMessage());
        }
    }

    @Override
    public ContactList getAContactList(Long id){
        try {
            Optional<ContactList> foundContactList = repository.findById(id);
            if (foundContactList.isEmpty()){
                throw new QuizApplicationException("Contact List with id '"+id+"' does not exist.");
            }
            return foundContactList.get();
        }catch (Exception e){
            throw new QuizApplicationException("Contact List not found because -> "+e.getMessage());
        }

    }
}
