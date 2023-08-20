package com.e.javatest.response;

import com.e.javatest.model.RegistryOfficeState;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
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
