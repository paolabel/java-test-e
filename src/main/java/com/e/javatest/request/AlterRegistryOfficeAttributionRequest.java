package com.e.javatest.request;

import java.util.Optional;
import lombok.Data;

@Data
public class AlterRegistryOfficeAttributionRequest {
    final String id;
    final Optional<String> name;
    final Optional<Boolean> situation;
}
