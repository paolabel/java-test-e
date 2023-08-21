package com.e.javatest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class AssignmentCreationRequest {
    @NotEmpty(message = "Campo 'id' não pode estar vazio")
    @Size(max = 20, message = "Campo 'id' deve ter até 20 caracteres")
    private final String id;

    @NotEmpty(message = "Campo 'nome' não pode estar vazio")
    @Size(max = 50, message = "Campo 'nome' deve ter até 50 caracteres")
    @JsonProperty("nome")
    private final String name;

    @JsonProperty("situacao")
    private final Optional<Boolean> state;
}
