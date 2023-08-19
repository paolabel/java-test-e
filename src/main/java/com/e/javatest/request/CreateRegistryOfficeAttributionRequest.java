package com.e.javatest.request;

import java.util.Optional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CreateRegistryOfficeAttributionRequest {
    @NotEmpty(message = "ID do registro não pode estar vazio")
    @Size(max = 20, message = "ID do registro deve ter até 20 caracteres")
    final String id;

    @NotEmpty(message = "Nome do registro não pode estar vazio")
    @Size(max = 50, message = "Nome do registro deve ter até 50 caracteres")
    @JsonProperty("nome")
    final String name;

    @JsonProperty("situacao")
    final Optional<Boolean> situation;
}
