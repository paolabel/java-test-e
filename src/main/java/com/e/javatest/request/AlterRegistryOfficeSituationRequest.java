package com.e.javatest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlterRegistryOfficeSituationRequest {

    @NotEmpty(message = "Novo valor para o nome do registro não pode estar vazio")
    @Size(max = 50, message = "Novo valor para o nome do registro deve ter até 50 caracteres")
    @JsonProperty("nome")
    private String name;
}
