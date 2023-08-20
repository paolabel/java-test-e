package com.e.javatest.controller;

import com.e.javatest.exception.DuplicateEntryException;
import com.e.javatest.exception.EntryNotFoundException;
import com.e.javatest.exception.InvalidIdForUpdateException;
import com.e.javatest.exception.NoFieldToUpdateException;
import com.e.javatest.model.RegistryOfficeAssignment;
import com.e.javatest.request.AssignmentAlterationRequest;
import com.e.javatest.request.AssignmentCreationRequest;
import com.e.javatest.response.AssignmentAlterationResponse;
import com.e.javatest.response.AssignmentCreationResponse;
import com.e.javatest.response.AssignmentLookupResponse;
import com.e.javatest.service.RegistryOfficeAssignmentService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public AssignmentCreationResponse createRegistryOfficeState(
            @RequestBody @Valid AssignmentCreationRequest request) throws DuplicateEntryException {
        RegistryOfficeAssignment newRegistryOfficeAssignment =
                registryOfficeAssignmentService.createRegistryOfficeAssignment(
                        request.getId(), request.getName(), request.getState());
        return new AssignmentCreationResponse(newRegistryOfficeAssignment);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AssignmentAlterationResponse updateRegistryOfficeAssignment(
            @PathVariable String id, @RequestBody @Valid AssignmentAlterationRequest request)
            throws InvalidIdForUpdateException, NoFieldToUpdateException {
        RegistryOfficeAssignment updatedAssignment =
                registryOfficeAssignmentService.updateRegistryOfficeAssignment(
                        id, request.getName(), request.getState());
        return new AssignmentAlterationResponse(updatedAssignment);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AssignmentLookupResponse getRegistryOfficeAssignment(@PathVariable String id)
            throws EntryNotFoundException {
        RegistryOfficeAssignment assignment =
                registryOfficeAssignmentService.getRegistryOfficeAssignment(id);
        return new AssignmentLookupResponse(assignment);
    }
}
