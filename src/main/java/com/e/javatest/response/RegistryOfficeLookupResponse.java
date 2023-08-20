package com.e.javatest.response;

import com.e.javatest.model.RegistryOffice;
import com.e.javatest.model.RegistryOfficeAssignment;
import com.e.javatest.model.RegistryOfficeState;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class RegistryOfficeLookupResponse {
    @JsonProperty("id")
    private final int id;

    @JsonProperty("nome")
    private final String name;

    @JsonProperty("observacao")
    @JsonInclude(Include.NON_NULL)
    private final String observation;

    @JsonProperty("situacao")
    private final RegistryOfficeState state;

    @JsonProperty("atribuicoes")
    private final List<RegistryOfficeAssignment> assignments;

    public RegistryOfficeLookupResponse(RegistryOffice registryOffice) {
        this.id = registryOffice.getId();
        this.name = registryOffice.getName();
        this.observation = registryOffice.getObservation();
        this.state = registryOffice.getState();
        this.assignments = registryOffice.getAssignments();
    }
}
