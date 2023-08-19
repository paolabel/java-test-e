package com.e.javatest.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AlterRegistryOfficeSituationRequest {
    @NotEmpty(message = "ID do registro não pode estar vazio")
    @Size(max = 20, message = "ID do registro deve ter até 20 caracteres")
    final String id;

    @NotEmpty(message = "Novo valor para o nome do registro não pode estar vazio")
    @Size(max = 50, message = "Novo valor para o nome do registro deve ter até 50 caracteres")
    @JsonProperty("nome")
    final String name;
}
