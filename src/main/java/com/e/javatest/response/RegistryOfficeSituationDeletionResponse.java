package com.e.javatest.response;

import lombok.Data;

@Data
public class RegistryOfficeSituationDeletionResponse {
    private final String message;
    private final int statusCode = 200;

    public RegistryOfficeSituationDeletionResponse(String id) {
        this.message = "A situação de cartório com id \\'" + id + "\\' foi deletada com sucesso.";
    }
}
