package com.e.javatest.request;

import java.util.Optional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AlterRegistryOfficeAttributionRequest {
    @NotEmpty(message = "ID do registro não pode estar vazio")
    @Size(max = 20, message = "ID do registro deve ter até 20 caracteres")
    final String id;

    @Size(max = 50, message = "Novo valor para o nome do registro deve ter até 50 caracteres")
    @JsonProperty("nome")
    final Optional<String> name;

    @JsonProperty("situacao")
    final Optional<Boolean> situation;
}
