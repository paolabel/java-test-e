package com.e.javatest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistryOfficeCreationRequest {
    @NotNull(message = "ID do registro não pode estar vazio")
    @Min(1)
    private final int id;

    @NotEmpty(message = "Nome do registro não pode estar vazio")
    @Size(max = 150, message = "Nome do registro deve ter até 50 caracteres")
    @JsonProperty("nome")
    private final String name;

    @JsonProperty("observacao")
    private final Optional<
                    @Size(
                            max = 250,
                            message =
                                    "Novo valor para observação do registro deve ter até 250"
                                            + " caracteres")
                    String>
            observation;

    @NotEmpty(message = "ID da situação do cartório não pode estar vazia")
    @JsonProperty("idSituacao")
    private final String stateId;

    @NotEmpty(message = "Lista de IDs de atribuições do cartório não pode estar vazia")
    @Size(min = 1)
    @JsonProperty("idsAtribuicoes")
    private final List<
                    @NotEmpty(message = "ID da atribuição do cartório não pode estar vazia") String>
            assignmentIdList;
}
