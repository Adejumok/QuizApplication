package com.africa.quizapp.dto.requests;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class MailRequest {
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
