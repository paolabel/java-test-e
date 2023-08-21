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
    @NotNull(message = "Campo 'id' não pode estar vazio")
    @Min(value = 1, message = "Campo 'id' deve ser positivo")
    private final int id;

    @NotEmpty(message = "Campo 'nome' não pode estar vazio")
    @Size(max = 150, message = "Campo 'nome' deve ter até 50 caracteres")
    @JsonProperty("nome")
    private final String name;

    @JsonProperty("observacao")
    private final Optional<
                    @Size(
                            max = 250,
                            message = "Campo 'observacao' deve ter até 250" + " caracteres")
                    String>
            observation;

    @NotEmpty(message = "Campo 'idSituacao' não pode estar vazio")
    @JsonProperty("idSituacao")
    private final String stateId;

    @NotEmpty(message = "Campo 'idsAtribuicoes' do cartório não pode estar vazia")
    @JsonProperty("idsAtribuicoes")
    private final List<
                    @NotEmpty(message = "ID da atribuição do cartório não pode estar vazia") String>
            assignmentIdList;
}
