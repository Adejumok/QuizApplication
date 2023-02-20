package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.RegisterContactListRequest;
import com.africa.quizapp.dto.responses.ContactListResponse;
import com.africa.quizapp.models.contactModels.ContactList;

import java.util.concurrent.CompletableFuture;

public interface ContactListService {
    CompletableFuture<ContactListResponse> registerContactListResponse(RegisterContactListRequest contactListRequest);
    CompletableFuture<ContactListResponse> addFormToContactListResponse(Long contactListId, Long formId);
    ContactList getAContactList(Long id);
}
