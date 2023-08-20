package com.e.javatest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class AlterRegistryOfficeAssignmentRequest {
    @NotEmpty(message = "ID do registro não pode estar vazio")
    @Size(max = 20, message = "ID do registro deve ter até 20 caracteres")
    private final String id;

    @Size(max = 50, message = "Novo valor para o nome do registro deve ter até 50 caracteres")
    @JsonProperty("nome")
    private final Optional<String> name;

    @JsonProperty("situacao")
    private final Optional<Boolean> situation;
}
