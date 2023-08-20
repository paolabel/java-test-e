package com.e.javatest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StateDeletionResponse {
    @JsonProperty("mensagem")
    private String message;

    public StateDeletionResponse(String id) {
        this.message = "A situação de cartório com id '" + id + "' foi deletada com sucesso.";
    }
}
