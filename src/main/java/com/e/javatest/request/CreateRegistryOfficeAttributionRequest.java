package com.e.javatest.request;

import java.util.Optional;
import lombok.Data;

@Data
public class CreateRegistryOfficeAttributionRequest {
    final String id;
    final String name;
    final Optional<Boolean> situation;
}
