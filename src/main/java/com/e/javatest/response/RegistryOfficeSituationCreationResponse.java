package com.e.javatest.response;

import lombok.Data;

@Data
public class RegistryOfficeSituationCreationResponse {
    final String message;
    final int statusCode = 201;

    public RegistryOfficeSituationCreationResponse(String id) {
        this.message = "A situação de cartório com id \"" + id + "\" foi cadastrada com sucesso.";
    }
}
