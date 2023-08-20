package com.e.javatest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistryOfficeUpdateRequest {

    private static final String MIN_NAME_SIZE_MESSAGE =
            "Novo valor para o nome do registro não pode ser vazio";
    private static final String MAX_NAME_SIZE_MESSAGE =
            "Novo valor para o nome do registro deve ter até 150 caracteres";
    private static final String MIN_OBS_SIZE_MESSAGE =
            "Novo valor para a observação do registro não pode ser vazio";
    private static final String MAX_OBS_SIZE_MESSAGE =
            "Novo valor para a observação do registro deve ter até 250 caracteres";
    private static final String MIN_ASSIGNMENT_LIST_SIZE_MESSAGE =
            "Nova lista de IDs de atribuições do cartório não pode estar vazia";

    @JsonProperty("nome")
    final Optional<
                    @Size(min = 1, message = MIN_NAME_SIZE_MESSAGE)
                    @Size(max = 150, message = MAX_NAME_SIZE_MESSAGE) String>
            name;

    @JsonProperty("observacao")
    private final Optional<
                    @Size(min = 1, message = MIN_OBS_SIZE_MESSAGE)
                    @Size(max = 250, message = MAX_OBS_SIZE_MESSAGE) String>
            observation;

    @JsonProperty("idSituacao")
    final Optional<String> stateId;

    @JsonProperty("idsAtribuicoes")
    final Optional<
                    @Size(min = 1, message = MIN_ASSIGNMENT_LIST_SIZE_MESSAGE) List<
                            @NotEmpty(message = "ID da atribuição do cartório não pode estar vazia")
                            String>>
            assignmentIdList;
}
