package com.africa.quizapp.dto.requests;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class RegisterUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String address;
}
