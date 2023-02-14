package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.ReportRequest;
import com.africa.quizapp.dto.responses.ReportResponse;
import com.africa.quizapp.exception.QuizApplicationException;
import com.africa.quizapp.models.QuizUser;
import com.africa.quizapp.models.quizModels.Quiz;
import com.africa.quizapp.models.quizModels.Report;
import com.africa.quizapp.repository.ReportRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService{
    private final ReportRepository repository;
    private final UserService userService;
    @Override
    public ReportResponse addUserToReport(ReportRequest request) {
        Report foundReport = repository.findById(request.getReportId()).orElse(null);
        if (foundReport != null){
            throw new QuizApplicationException("Report with id "+request.getReportId()+" already exist.");
        }
        QuizUser foundUser = userService.getAUserById(request.getUserId());
        Report report = new Report();
        report.setQuizUser(foundUser);
        report.setName(foundUser.getFirstName());
        Report savedReport = repository.save(report);
        return ReportResponse.builder()
                .message("User successfully added to Report.")
                .report(savedReport)
                .build();
    }

    @Override
    public Report getAReport(long id) {
        Optional<Report> foundReport = repository.findById(id);
        if (foundReport.isEmpty()){
            throw new QuizApplicationException("Report with id '"+id+"' does not exist.");
        }
        return foundReport.get();
    }

    @Override
    public ReportResponse deleteReportResponse(long id) {
        Report foundReport = getAReport(id);
        repository.delete(foundReport);
        return ReportResponse.builder()
                .message("Report with id '"+id+"' has been successfully deleted.")
                .build();
    }
}
