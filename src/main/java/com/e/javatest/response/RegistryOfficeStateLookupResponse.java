package com.e.javatest.response;

import com.e.javatest.model.RegistryOfficeState;
import lombok.Data;

@Data
public class RegistryOfficeStateLookupResponse {
    private final String id;
    private final String name;

    public RegistryOfficeStateLookupResponse(RegistryOfficeState state) {
        this.id = state.getId();
        this.name = state.getName();
    }
}
