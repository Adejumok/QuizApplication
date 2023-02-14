package com.africa.quizapp.dto.requests;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class RegisterContactListRequest {
    private Long contactListId;
}
