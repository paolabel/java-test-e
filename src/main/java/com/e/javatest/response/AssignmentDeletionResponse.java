package com.e.javatest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder(alphabetic = true)
public class AssignmentDeletionResponse {
    @JsonProperty("mensagem")
    private String message;

    public AssignmentDeletionResponse(String id) {
        this.message = "A atribuição de cartório com id '" + id + "' foi deletada com sucesso.";
    }
}
