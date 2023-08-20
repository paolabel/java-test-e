package com.e.javatest.response;

import com.e.javatest.repository.IdAndNameOnly;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class RegistryOfficeListResponse {
    @JsonProperty("dados")
    private List<IdAndNameOnly> searchResult;

    public RegistryOfficeListResponse(List<IdAndNameOnly> searchResult) {
        this.searchResult = searchResult;
    }
}
