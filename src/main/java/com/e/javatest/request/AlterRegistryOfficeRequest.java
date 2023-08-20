package com.e.javatest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class AlterRegistryOfficeRequest {

    @Size(max = 150, message = "Novo valor para o nome do registro deve ter até 150 caracteres")
    @JsonProperty("nome")
    final Optional<String> name;

    @Size(max = 20, message = "Novo valor para o nome do registro deve ter até 20 caracteres")
    @JsonProperty("idSituacao")
    final Optional<String> situationId;

    @JsonProperty("idsAtribuicoes")
    final Optional<List<String>> assignmentIdList;
}
