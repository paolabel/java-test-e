package com.e.javatest.response;

import com.e.javatest.model.RegistryOfficeAssignment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AssignmentCreationResponse {
    @JsonProperty("mensagem")
    private final String message;

    @JsonProperty("dados")
    private final RegistryOfficeAssignment data;

    public AssignmentCreationResponse(RegistryOfficeAssignment newRegistryOfficeAssignment) {
        this.message =
                "A atribuição de cartório com id '"
                        + newRegistryOfficeAssignment.getId()
                        + "' foi cadastrada com sucesso.";
        this.data = newRegistryOfficeAssignment;
    }
}
