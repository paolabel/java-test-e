package com.e.javatest.response;

import com.e.javatest.model.RegistryOfficeState;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RegistryOfficeStateAlterationResponse {
    @JsonProperty("mensagem")
    private final String message;

    @JsonProperty("dados")
    private final RegistryOfficeState data;

    public RegistryOfficeStateAlterationResponse(RegistryOfficeState updatedState) {
        this.message =
                "A situação de cartório com id '"
                        + updatedState.getId()
                        + "' foi alterada com sucesso.";
        this.data = updatedState;
    }
}
