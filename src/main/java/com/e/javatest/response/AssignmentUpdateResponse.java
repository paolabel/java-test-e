package com.e.javatest.response;

import com.e.javatest.model.RegistryOfficeAssignment;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AssignmentUpdateResponse {
    @JsonProperty("mensagem")
    private final String message;

    @JsonProperty("dados")
    private final RegistryOfficeAssignment data;

    public AssignmentUpdateResponse(RegistryOfficeAssignment updatedAssignment) {
        this.message =
                "A atribuição de cartório com id '"
                        + updatedAssignment.getId()
                        + "' foi alterada com sucesso.";
        this.data = updatedAssignment;
    }
}
