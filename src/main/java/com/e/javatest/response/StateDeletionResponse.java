package com.e.javatest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder(alphabetic = true)
public class StateDeletionResponse {
    @JsonProperty("mensagem")
    private String message;

    public StateDeletionResponse(String id) {
        this.message = "A situação de cartório com id '" + id + "' foi deletada com sucesso.";
    }
}
