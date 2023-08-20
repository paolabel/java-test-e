package com.e.javatest.response;

import com.e.javatest.model.RegistryOfficeSituation;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RegistryOfficeSituationAlterationResponse {
    @JsonProperty("message")
    private final String message;

    @JsonProperty("statusCode")
    private final int statusCode = 200;

    @JsonProperty("result")
    private final RegistryOfficeSituation result;

    public RegistryOfficeSituationAlterationResponse(RegistryOfficeSituation updatedSituation) {
        this.message =
                "A situação de cartório com id \\'"
                        + updatedSituation.getId()
                        + "\\' foi alterada com sucesso.";
        this.result = updatedSituation;
    }
}
