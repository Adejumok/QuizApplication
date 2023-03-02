package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.ReportRequest;
import com.africa.quizapp.dto.responses.ReportResponse;
import com.africa.quizapp.exception.QuizApplicationException;
import com.africa.quizapp.models.QuizUser;
import com.africa.quizapp.models.quizModels.Report;
import com.africa.quizapp.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{
    private final ReportRepository repository;
    private final UserService userService;
    private final Executor executor;
    @Override
    public CompletableFuture<ReportResponse> addUserToReport(ReportRequest request) {
        try {
            return CompletableFuture.supplyAsync(()->{
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
            }, executor);
        }catch (Exception e){
            throw new QuizApplicationException("User is not added to report because: "+e.getMessage());
        }

    }

    @Override
    public Report getAReport(long id) {
        try {
            Optional<Report> foundReport = repository.findById(id);
            if (foundReport.isEmpty()){
                throw new QuizApplicationException("Report with id '"+id+"' does not exist.");
            }
            return foundReport.get();
        }catch (Exception e){
            throw new QuizApplicationException("Report not found due to: "+e.getMessage());
        }

    }

    @Override
    public CompletableFuture<ReportResponse> deleteReportResponse(long id) {
        try {
            return CompletableFuture.supplyAsync(()->{
                Report foundReport = getAReport(id);
                repository.delete(foundReport);
                return ReportResponse.builder()
                        .message("Report with id '"+id+"' has been successfully deleted.")
                        .build();
            }, executor);
        }catch (Exception e){
            throw new QuizApplicationException("Report not deleted because: "+e.getMessage());
        }

    }
}
