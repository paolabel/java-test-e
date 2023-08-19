package com.e.javatest.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class DeleteByStringIdRequest {
    @NotEmpty(message = "ID do registro não pode estar vazio")
    @Size(max = 20)
    final String id;
}
