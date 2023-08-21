package com.e.javatest.response;

import com.e.javatest.repository.IdAndNameOnly;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;
import lombok.Data;

@Data
@JsonPropertyOrder(alphabetic = true)
public class ListAllResponse {
    @JsonProperty("pagina")
    private int page;

    @JsonProperty("dados")
    private List<IdAndNameOnly> searchResult;

    public ListAllResponse(int page, List<IdAndNameOnly> searchResult) {
        this.page = page;
        this.searchResult = searchResult;
    }
}
