package com.e.javatest.response;

import com.e.javatest.model.RegistryOfficeAssignment;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder(alphabetic = true)
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
