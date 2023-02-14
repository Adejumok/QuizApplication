package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.RegisterContactListRequest;
import com.africa.quizapp.dto.responses.ContactListResponse;
import com.africa.quizapp.models.contactModels.ContactList;

public interface ContactListService {
    ContactListResponse registerContactListResponse(RegisterContactListRequest contactListRequest);
    ContactListResponse addFormToContactListResponse(Long contactListId, Long formId);
    ContactList getAContactList(Long id);
}
