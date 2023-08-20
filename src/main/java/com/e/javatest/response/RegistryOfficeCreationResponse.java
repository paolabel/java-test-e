package com.e.javatest.response;

import com.e.javatest.model.RegistryOffice;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder(alphabetic = true)
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
