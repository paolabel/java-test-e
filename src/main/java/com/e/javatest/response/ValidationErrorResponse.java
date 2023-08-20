package com.e.javatest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class ValidationErrorResponse {
    @JsonProperty("mensagem")
    private String message;

    @JsonProperty("erros")
    private List<String> validationErrors;

    public ValidationErrorResponse(String message, List<String> validationErrors) {
        this.message = message;
        this.validationErrors = validationErrors;
    }
}
