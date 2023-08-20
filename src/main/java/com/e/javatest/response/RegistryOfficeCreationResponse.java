package com.e.javatest.response;

import com.e.javatest.model.RegistryOffice;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegistryOfficeCreationResponse {
    @JsonProperty("message")
    private final String message;

    @JsonProperty("status")
    private final int statusCode = 201;

    @JsonProperty("data")
    private final RegistryOffice data;

    public RegistryOfficeCreationResponse(RegistryOffice newRegistryOffice) {
        this.message =
                "O cartório com id '"
                        + Integer.toString(newRegistryOffice.getId())
                        + "' foi cadastrado com sucesso.";
        this.data = newRegistryOffice;
    }
}
