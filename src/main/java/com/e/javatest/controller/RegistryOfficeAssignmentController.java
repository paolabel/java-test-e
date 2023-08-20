package com.e.javatest.controller;

import com.e.javatest.exception.DuplicateEntryException;
import com.e.javatest.exception.EntryNotFoundException;
import com.e.javatest.exception.EntryStillBeingUsedException;
import com.e.javatest.exception.InvalidIdException;
import com.e.javatest.exception.NoFieldToUpdateException;
import com.e.javatest.model.RegistryOfficeAssignment;
import com.e.javatest.request.AssignmentCreationRequest;
import com.e.javatest.request.AssignmentUpdateRequest;
import com.e.javatest.response.AssignmentCreationResponse;
import com.e.javatest.response.AssignmentDeletionResponse;
import com.e.javatest.response.AssignmentLookupResponse;
import com.e.javatest.response.AssignmentUpdateResponse;
import com.e.javatest.service.RegistryOfficeAssignmentService;
import com.e.javatest.service.RegistryOfficeService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    @Autowired RegistryOfficeService registryOfficeService;

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
    public AssignmentUpdateResponse updateRegistryOfficeAssignment(
            @PathVariable String id, @RequestBody @Valid AssignmentUpdateRequest request)
            throws InvalidIdException, NoFieldToUpdateException {
        RegistryOfficeAssignment updatedAssignment =
                registryOfficeAssignmentService.updateRegistryOfficeAssignment(
                        id, request.getName(), request.getState());
        return new AssignmentUpdateResponse(updatedAssignment);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AssignmentDeletionResponse deleteRegistryOfficeAssignment(@PathVariable String id)
            throws InvalidIdException, EntryStillBeingUsedException {
        boolean cantBeDeleted =
                registryOfficeService.checkifAnyRegistryOfficeContainsAssignment(id);
        if (cantBeDeleted) {
            throw new EntryStillBeingUsedException("Registro utilizado em outro cadastro.");
        }
        String deletedId = registryOfficeAssignmentService.deleteRegistryOfficeAssignment(id);
        return new AssignmentDeletionResponse(deletedId);
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
