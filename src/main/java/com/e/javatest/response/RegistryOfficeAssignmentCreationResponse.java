package com.e.javatest.response;

import lombok.Data;

@Data
public class RegistryOfficeAssignmentCreationResponse {
    private final String message;
    private final int statusCode = 201;

    public RegistryOfficeAssignmentCreationResponse(String id) {
        this.message =
                "A atribuição de cartório com id \\'" + id + "\\' foi cadastrada com sucesso.";
    }
}
