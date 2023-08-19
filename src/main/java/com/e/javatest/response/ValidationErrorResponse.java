package com.e.javatest.response;

import java.util.List;

import lombok.Data;

@Data
public class ValidationErrorResponse {
    private String message;
    private List<String> validationErrors;

    public ValidationErrorResponse(String message, List<String> validationErrors) {
        this.message = message;
        this.validationErrors = validationErrors;
    }
}
