package com.e.javatest.response;

import com.e.javatest.model.RegistryOfficeAssignment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegistryOfficeAssignmentCreationResponse {
    @JsonProperty("mensagem")
    private final String message;

    @JsonProperty("dados")
    private final RegistryOfficeAssignment data;

    public RegistryOfficeAssignmentCreationResponse(
            RegistryOfficeAssignment newRegistryOfficeAssignment) {
        this.message =
                "A atribuição de cartório com id '"
                        + newRegistryOfficeAssignment.getId()
                        + "' foi cadastrada com sucesso.";
        this.data = newRegistryOfficeAssignment;
    }
}
