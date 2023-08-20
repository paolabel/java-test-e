package com.e.javatest.response;

import com.e.javatest.repository.IdAndNameOnly;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;
import lombok.Data;

@Data
@JsonPropertyOrder(alphabetic = true)
public class ListAllResponse {
    @JsonProperty("dados")
    private List<IdAndNameOnly> searchResult;

    public ListAllResponse(List<IdAndNameOnly> searchResult) {
        this.searchResult = searchResult;
    }
}
