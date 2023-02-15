package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.RegisterContactListRequest;
import com.africa.quizapp.dto.responses.ContactListResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Slf4j
public class ContactListServiceTest {
    @Autowired
    private ContactListService contactListService;
    private RegisterContactListRequest contactListRequest;

    @BeforeEach
    void setUp(){
        contactListRequest = RegisterContactListRequest.builder()
                .contactListId(1L)
                .build();
    }

    @Test
    void registerContactListRequestTest(){
        ContactListResponse response = contactListService.registerContactListResponse(contactListRequest);
        log.info("{}", response.getMessage());
        assertThat(response).isNotNull();
    }

    @Test
    void addFormToContactListRequestTest(){
        ContactListResponse response = contactListService.addFormToContactListResponse(1L, 4L);
        log.info("{}", response.getMessage());
        assertThat(response).isNotNull();
    }
}
