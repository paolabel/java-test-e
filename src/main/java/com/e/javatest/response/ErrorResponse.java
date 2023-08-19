package com.e.javatest.response;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;

    public ErrorResponse(Throwable exception) {
        this.message = exception.getMessage();
    }
}
