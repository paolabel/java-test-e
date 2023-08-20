package com.e.javatest.response;

import com.e.javatest.model.RegistryOffice;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegistryOfficeCreationResponse {
    @JsonProperty("mensagem")
    private final String message;

    @JsonProperty("dados")
    private final RegistryOffice data;

    public RegistryOfficeCreationResponse(RegistryOffice newRegistryOffice) {
        this.message =
                "O cartório com id '"
                        + Integer.toString(newRegistryOffice.getId())
                        + "' foi cadastrado com sucesso.";
        this.data = newRegistryOffice;
    }
}
