package com.e.javatest.response;

import com.e.javatest.model.RegistryOfficeState;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder(alphabetic = true)
public class StateLookupResponse {
    @JsonProperty("id")
    private final String id;

    @JsonProperty("nome")
    private final String name;

    public StateLookupResponse(RegistryOfficeState state) {
        this.id = state.getId();
        this.name = state.getName();
    }
}
