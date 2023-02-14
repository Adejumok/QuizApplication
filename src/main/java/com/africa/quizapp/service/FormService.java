package com.africa.quizapp.service;

import com.africa.quizapp.dto.requests.FormRequest;
import com.africa.quizapp.dto.responses.FormResponse;
import com.africa.quizapp.models.contactModels.Form;

public interface FormService {
    FormResponse formResponse(FormRequest formRequest);
    Form locateAForm(Long id);
}
