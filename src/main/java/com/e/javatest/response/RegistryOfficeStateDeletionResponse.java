package com.e.javatest.response;

import lombok.Data;

@Data
public class RegistryOfficeStateDeletionResponse {
    private final String message;
    private final int statusCode = 200;

    public RegistryOfficeStateDeletionResponse(String id) {
        this.message = "A situação de cartório com id \\'" + id + "\\' foi deletada com sucesso.";
    }
}
