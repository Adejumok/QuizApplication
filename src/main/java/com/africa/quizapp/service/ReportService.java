package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.ReportRequest;
import com.africa.quizapp.dto.responses.ReportResponse;
import com.africa.quizapp.models.quizModels.Report;

public interface ReportService {

    ReportResponse addUserToReport(ReportRequest request);
    Report getAReport(long id);

    ReportResponse deleteReportResponse(long id);
}
