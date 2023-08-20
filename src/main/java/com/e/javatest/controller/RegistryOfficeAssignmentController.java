package com.e.javatest.controller;

import com.e.javatest.exception.DuplicateEntryException;
import com.e.javatest.model.RegistryOfficeAssignment;
import com.e.javatest.request.CreateRegistryOfficeAssignmentRequest;
import com.e.javatest.response.RegistryOfficeAssignmentCreationResponse;
import com.e.javatest.service.RegistryOfficeAssignmentService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/atribuicao-cartorio", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistryOfficeAssignmentController {

    @Autowired RegistryOfficeAssignmentService registryOfficeAssignmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegistryOfficeAssignmentCreationResponse createRegistryOfficeState(
            @RequestBody @Valid CreateRegistryOfficeAssignmentRequest request)
            throws DuplicateEntryException {
        RegistryOfficeAssignment newRegistryOfficeAssignment =
                registryOfficeAssignmentService.createRegistryOfficeAssignment(
                        request.getId(), request.getName(), request.getState());
        return new RegistryOfficeAssignmentCreationResponse(newRegistryOfficeAssignment);
    }
}
