package com.e.javatest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class AlterRegistryOfficeAssignmentRequest {

    @Size(min = 1, message = "Novo valor para o nome do registro não pode ser vazio")
    @Size(max = 50, message = "Novo valor para o nome do registro deve ter até 50 caracteres")
    @JsonProperty("nome")
    private final Optional<String> name;

    @JsonProperty("situacao")
    private final Optional<Boolean> state;
}
