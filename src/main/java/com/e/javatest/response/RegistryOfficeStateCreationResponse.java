package com.e.javatest.response;

import lombok.Data;

@Data
public class RegistryOfficeStateCreationResponse {
    private final String message;
    private final int statusCode = 201;

    public RegistryOfficeStateCreationResponse(String id) {
        this.message = "A situação de cartório com id \\'" + id + "\\' foi cadastrada com sucesso.";
    }
}
