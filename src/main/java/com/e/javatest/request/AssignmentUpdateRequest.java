package com.e.javatest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class AssignmentUpdateRequest {

    private static final String MIN_NAME_SIZE_MESSAGE =
            "Novo valor para o nome do registro não pode ser vazio";
    private static final String MAX_NAME_SIZE_MESSAGE =
            "Novo valor para o nome do registro deve ter até 50 caracteres";

    @JsonProperty("nome")
    private final Optional<
                    @Size(min = 1, message = MIN_NAME_SIZE_MESSAGE)
                    @Size(max = 50, message = MAX_NAME_SIZE_MESSAGE) String>
            name;

    @JsonProperty("situacao")
    private final Optional<Boolean> state;
}
