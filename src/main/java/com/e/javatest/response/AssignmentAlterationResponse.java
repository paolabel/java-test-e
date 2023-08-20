package com.e.javatest.response;

import com.e.javatest.model.RegistryOfficeAssignment;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AssignmentAlterationResponse {
    @JsonProperty("mensagem")
    private final String message;

    @JsonProperty("dados")
    private final RegistryOfficeAssignment data;

    public AssignmentAlterationResponse(RegistryOfficeAssignment updatedAssignment) {
        this.message =
                "A atribuição de cartório com id '"
                        + updatedAssignment.getId()
                        + "' foi alterada com sucesso.";
        this.data = updatedAssignment;
    }
}