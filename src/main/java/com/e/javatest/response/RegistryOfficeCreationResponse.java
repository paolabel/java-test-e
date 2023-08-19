package com.e.javatest.response;

import lombok.Data;

@Data
public class RegistryOfficeCreationResponse {
    final String message;
    final int statusCode = 201;

    public RegistryOfficeCreationResponse(int id) {
        this.message =
                "O cartório com id '" + Integer.toString(id) + "' foi cadastrado com sucesso.";
    }
}
