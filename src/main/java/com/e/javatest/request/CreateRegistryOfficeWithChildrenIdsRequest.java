package com.e.javatest.request;

import java.util.List;
import lombok.Data;

@Data
public class CreateRegistryOfficeWithChildrenIdsRequest {
    final int id;
    final String name;
    final String situationId;
    final List<String> attributionIdList;
}
