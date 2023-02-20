package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.ReportRequest;
import com.africa.quizapp.dto.responses.ReportResponse;
import com.africa.quizapp.models.quizModels.Report;

import java.util.concurrent.CompletableFuture;

public interface ReportService {

    CompletableFuture<ReportResponse> addUserToReport(ReportRequest request);
    Report getAReport(long id);

    CompletableFuture<ReportResponse> deleteReportResponse(long id);
}
