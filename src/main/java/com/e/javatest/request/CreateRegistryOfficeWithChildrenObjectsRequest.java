package com.e.javatest.request;

import java.util.List;
import lombok.Data;

@Data
public class CreateRegistryOfficeWithChildrenObjectsRequest {
    final int id;
    final String name;
    final CreateRegistryOfficeSituationRequest situation;
    final List<CreateRegistryOfficeAttributionRequest> attributionList;
}
