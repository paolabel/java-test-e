package com.e.javatest.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class DeleteByIntIdRequest {
    @NotEmpty(message = "ID do registro não pode estar vazio")
    @Min(1)
    final int id;
}
