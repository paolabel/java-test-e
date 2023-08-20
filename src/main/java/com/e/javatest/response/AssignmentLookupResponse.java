package com.e.javatest.response;

import com.e.javatest.model.RegistryOfficeAssignment;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder(alphabetic = true)
public class AssignmentLookupResponse {
    @JsonProperty("id")
    private final String id;

    @JsonProperty("nome")
    private final String name;

    @JsonProperty("situacao")
    private final boolean state;

    public AssignmentLookupResponse(RegistryOfficeAssignment assignment) {
        this.id = assignment.getId();
        this.name = assignment.getName();
        this.state = assignment.getState();
    }
}
