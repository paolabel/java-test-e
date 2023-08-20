package com.e.javatest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateRegistryOfficeWithChildrenObjectsRequest {
    @NotEmpty(message = "ID do cartório não pode estar vazio")
    @Min(1)
    private final int id;

    @NotEmpty(message = "Nome do cartório não pode estar vazio")
    @Size(max = 150, message = "Nome do registro deve ter até 150 caracteres")
    @JsonProperty("nome")
    private final String name;

    @Size(max = 250, message = "Observação do registro deve ter até 250 caracteres")
    @JsonProperty("observacao")
    private final Optional<String> observation;

    @NotEmpty(message = "Situação do cartório a ser criada não pode estar vazia")
    @JsonProperty("situacao")
    private final CreateRegistryOfficeSituationRequest situation;

    @NotEmpty(message = "Lista de atribuições do cartório a serem criadas não pode estar vazia")
    @Size(min = 1)
    @JsonProperty("atribuicoes")
    private final List<CreateRegistryOfficeAssignmentRequest> assignmentList;
}
