package com.e.javatest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ErrorResponse {
    @JsonProperty("mensagem")
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public ErrorResponse(Throwable exception) {
        this.message = exception.getMessage();
    }
}
