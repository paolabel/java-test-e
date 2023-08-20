package com.e.javatest.response;

import com.e.javatest.model.RegistryOfficeState;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RegistryOfficeStateAlterationResponse {
    @JsonProperty("message")
    private final String message;

    @JsonProperty("statusCode")
    private final int statusCode = 200;

    @JsonProperty("result")
    private final RegistryOfficeState result;

    public RegistryOfficeStateAlterationResponse(RegistryOfficeState updatedState) {
        this.message =
                "A situação de cartório com id '"
                        + updatedState.getId()
                        + "' foi alterada com sucesso.";
        this.result = updatedState;
    }
}
