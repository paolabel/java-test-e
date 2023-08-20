package com.e.javatest.response;

import com.e.javatest.model.RegistryOfficeState;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder(alphabetic = true)
public class StateCreationResponse {
    @JsonProperty("mensagem")
    private final String message;

    @JsonProperty("dados")
    private final RegistryOfficeState data;

    public StateCreationResponse(RegistryOfficeState newRegistryOfficeState) {
        this.message =
                "A situação de cartório com id '"
                        + newRegistryOfficeState.getId()
                        + "' foi cadastrada com sucesso.";
        this.data = newRegistryOfficeState;
    }
}
