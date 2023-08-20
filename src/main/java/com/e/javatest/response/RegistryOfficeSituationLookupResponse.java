package com.e.javatest.response;

import com.e.javatest.model.RegistryOfficeSituation;
import lombok.Data;

@Data
public class RegistryOfficeSituationLookupResponse {
    private final String id;
    private final String name;

    public RegistryOfficeSituationLookupResponse(RegistryOfficeSituation situation) {
        this.id = situation.getId();
        this.name = situation.getName();
    }
}
