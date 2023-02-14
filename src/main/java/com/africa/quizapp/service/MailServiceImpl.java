package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.MailRequest;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService{
  private final JavaMailSender javaMailSender;

    private final String SENDER = System.getenv("SENDER");
    @Override
    public String sendSimpleMail(MailRequest request) {
        try {
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            mailMessage.setFrom(SENDER);
            mailMessage.setTo(request.getRecipient());
            mailMessage.setText(request.getMsgBody());
            mailMessage.setSubject(request.getSubject());

            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }

        catch (Exception e) {
            return "Error while Sending Mail";
    }
    }

}
