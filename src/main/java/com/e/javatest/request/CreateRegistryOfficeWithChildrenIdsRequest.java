package com.e.javatest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateRegistryOfficeWithChildrenIdsRequest {
    @NotEmpty(message = "ID do registro não pode estar vazio")
    @Min(0)
    final int id;

    @NotEmpty(message = "Nome do registro não pode estar vazio")
    @Size(max = 150, message = "Nome do registro deve ter até 50 caracteres")
    @JsonProperty("nome")
    final String name;

    @Size(max = 250, message = "Observação do registro deve ter até 250 caracteres")
    @JsonProperty("observacao")
    final Optional<String> observation;

    @NotEmpty(message = "ID da situação do cartório não pode estar vazia")
    @JsonProperty("idSituacao")
    final String situationId;

    @NotEmpty(message = "Lista de IDs de atribuições do cartório não pode estar vazia")
    @Size(min = 1)
    @JsonProperty("idsAtribuicoes")
    final List<String> attributionIdList;
}
