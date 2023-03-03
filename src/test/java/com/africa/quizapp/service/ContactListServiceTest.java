package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.RegisterContactListRequest;
import com.africa.quizapp.dto.responses.ContactListResponse;
import com.africa.quizapp.dto.responses.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Slf4j
public class ContactListServiceTest {
    @Autowired
    private ContactListService contactListService;
    private RegisterContactListRequest contactListRequest;

    @BeforeEach
    void setUp(){
        contactListRequest = RegisterContactListRequest.builder()
                .contactListId(2L)
                .build();
    }

    @Test
    void registerContactListRequestTest(){
        CompletableFuture<ContactListResponse> response = contactListService.registerContactListResponse(contactListRequest);
        ContactListResponse jointResponse =  response.join();
        assertThat(response).isNotNull();
        assertEquals(jointResponse.getMessage(), "Contact List registered successfully.");
    }

    @Test
    void registerSameContactRequestGetErrorTest(){
        CompletableFuture<ContactListResponse> response = contactListService.registerContactListResponse(contactListRequest);
        assertThat(response).isNotNull();
        assertThrows(CompletionException.class, response::join);
    }

    @Test
    void addFormToContactListRequestTest(){
        CompletableFuture<ContactListResponse> response = contactListService.addFormToContactListResponse(1L, 1L);
        ContactListResponse jointResponse =  response.join();
        assertThat(response).isNotNull();
        assertEquals(jointResponse.getMessage(), "Form successfully added to Contact List with id '"+1L+"'!");
    }
}
