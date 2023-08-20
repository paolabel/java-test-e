package com.e.javatest.response;

import com.e.javatest.model.RegistryOfficeState;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegistryOfficeStateLookupResponse {
    @JsonProperty("id")
    private final String id;

    @JsonProperty("nome")
    private final String name;

    public RegistryOfficeStateLookupResponse(RegistryOfficeState state) {
        this.id = state.getId();
        this.name = state.getName();
    }
}
