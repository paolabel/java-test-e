package com.e.javatest.response;

import com.e.javatest.model.RegistryOfficeAssignment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegistryOfficeAssignmentCreationResponse {
    @JsonProperty("message")
    private final String message;

    @JsonProperty("status")
    private final int statusCode = 201;

    @JsonProperty("data")
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
