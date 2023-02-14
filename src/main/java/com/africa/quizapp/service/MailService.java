package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.MailRequest;

public interface MailService {
    String sendSimpleMail(MailRequest request);
}
