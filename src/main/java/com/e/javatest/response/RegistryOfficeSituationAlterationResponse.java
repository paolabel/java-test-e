package com.e.javatest.response;

import com.e.javatest.model.RegistryOfficeSituation;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RegistryOfficeSituationAlterationResponse {
    @JsonProperty("message")
    final String message;

    @JsonProperty("statusCode")
    final int statusCode = 200;

    @JsonProperty("result")
    final RegistryOfficeSituation result;

    public RegistryOfficeSituationAlterationResponse(RegistryOfficeSituation updatedSituation) {
        this.message =
                "A situação de cartório com id '"
                        + updatedSituation.getId()
                        + "' foi alterada com sucesso.";
        this.result = updatedSituation;
    }
}
