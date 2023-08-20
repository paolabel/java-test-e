package com.e.javatest.response;

import com.e.javatest.model.RegistryOffice;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegistryOfficeUpdateResponse {
    @JsonProperty("mensagem")
    private final String message;

    @JsonProperty("dados")
    private final RegistryOffice data;

    public RegistryOfficeUpdateResponse(RegistryOffice updatedRegistryOffice) {
        this.message =
                "O cartório com id '"
                        + updatedRegistryOffice.getId()
                        + "' foi alterado com sucesso.";
        this.data = updatedRegistryOffice;
    }
}
