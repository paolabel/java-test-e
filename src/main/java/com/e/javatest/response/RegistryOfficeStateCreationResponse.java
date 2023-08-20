package com.e.javatest.response;

import com.e.javatest.model.RegistryOfficeState;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegistryOfficeStateCreationResponse {
    @JsonProperty("mensagem")
    private final String message;

    @JsonProperty("dados")
    private final RegistryOfficeState data;

    public RegistryOfficeStateCreationResponse(RegistryOfficeState newRegistryOfficeState) {
        this.message =
                "A situação de cartório com id '"
                        + newRegistryOfficeState.getId()
                        + "' foi cadastrada com sucesso.";
        this.data = newRegistryOfficeState;
    }
}
