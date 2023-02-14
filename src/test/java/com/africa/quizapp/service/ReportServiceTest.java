package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.ReportRequest;
import com.africa.quizapp.dto.responses.ReportResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Slf4j
public class ReportServiceTest {
    @Autowired
    private ReportService reportService;
    private ReportRequest request;

    @BeforeEach
    void setUp(){
        request = ReportRequest.builder()
                .reportId(2L)
                .userId(152L)
                .build();
    }

    @Test
    void addUserToReportTest(){
        ReportResponse response = reportService.addUserToReport(request);
        log.info("{}", response.getMessage());
        assertThat(response).isNotNull();
    }

    @Test
    void deleteReportTest(){
        ReportResponse response = reportService.deleteReportResponse(1L);
        log.info("{}", response.getMessage());
        assertThat(response).isNotNull();
    }
}
