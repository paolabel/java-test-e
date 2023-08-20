package com.e.javatest.response;

import com.e.javatest.model.RegistryOfficeAssignment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegistryOfficeAssignmentLookupResponse {
    @JsonProperty("id")
    private final String id;

    @JsonProperty("nome")
    private final String name;

    @JsonProperty("situacao")
    private final boolean state;

    public RegistryOfficeAssignmentLookupResponse(RegistryOfficeAssignment assignment) {
        this.id = assignment.getId();
        this.name = assignment.getName();
        this.state = assignment.getState();
    }
}
