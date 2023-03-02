package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.ReportRequest;
import com.africa.quizapp.dto.responses.ReportResponse;
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
public class ReportServiceTest {
    @Autowired
    private ReportService reportService;
    private ReportRequest request;

    @BeforeEach
    void setUp(){
        request = ReportRequest.builder()
                .reportId(1L)
                .userId(2L)
                .build();
    }

    @Test
    void addUserToReportTest(){
        CompletableFuture<ReportResponse> response = reportService.addUserToReport(request);
        ReportResponse jointResponse =  response.join();
        assertThat(response).isNotNull();
        assertEquals(jointResponse.getMessage(), "User successfully added to Report.");
    }
    @Test
    void addSameReportRequestGetErrorTest(){
        CompletableFuture<ReportResponse> response = reportService.addUserToReport(request);
        assertThat(response).isNotNull();
        assertThrows(CompletionException.class, response::join);
    }

    @Test
    void deleteReportTest(){
        CompletableFuture<ReportResponse> response = reportService.deleteReportResponse(1L);
        ReportResponse jointResponse =  response.join();
        assertThat(response).isNotNull();
        assertEquals(jointResponse.getMessage(), "Report with id '"+1L+"' has been successfully deleted.");
    }
}
