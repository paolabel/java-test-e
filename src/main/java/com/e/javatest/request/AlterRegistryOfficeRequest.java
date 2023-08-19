package com.e.javatest.request;

import java.util.List;
import java.util.Optional;
import lombok.Data;

@Data
public class AlterRegistryOfficeRequest {
    final int id;
    final Optional<String> name;
    final Optional<String> situationId;
    final Optional<List<String>> attributionIdList;
}
