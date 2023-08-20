package com.e.javatest.controller;

import com.e.javatest.exception.DuplicateEntryException;
import com.e.javatest.exception.EntryNotFoundException;
import com.e.javatest.exception.EntryStillBeingUsedException;
import com.e.javatest.exception.InvalidIdForUpdateException;
import com.e.javatest.model.RegistryOfficeState;
import com.e.javatest.request.AlterRegistryOfficeStateRequest;
import com.e.javatest.request.CreateRegistryOfficeStateRequest;
import com.e.javatest.response.RegistryOfficeStateAlterationResponse;
import com.e.javatest.response.RegistryOfficeStateCreationResponse;
import com.e.javatest.response.RegistryOfficeStateDeletionResponse;
import com.e.javatest.response.RegistryOfficeStateLookupResponse;
import com.e.javatest.service.RegistryOfficeService;
import com.e.javatest.service.RegistryOfficeStateService;
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
@RequestMapping(path = "/situacao-cartorio", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistryOfficeStateController {
    @Autowired RegistryOfficeStateService registryOfficeStateService;
    @Autowired RegistryOfficeService registryOfficeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegistryOfficeStateCreationResponse createRegistryOfficeState(
            @RequestBody @Valid CreateRegistryOfficeStateRequest request)
            throws DuplicateEntryException {
        RegistryOfficeState newRegistryOfficeState =
                registryOfficeStateService.createRegistryOfficeState(
                        request.getId(), request.getName());
        return new RegistryOfficeStateCreationResponse(newRegistryOfficeState);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RegistryOfficeStateAlterationResponse updateRegistryOfficeState(
            @PathVariable String id, @RequestBody @Valid AlterRegistryOfficeStateRequest request)
            throws InvalidIdForUpdateException {
        RegistryOfficeState updatedState =
                registryOfficeStateService.updateRegistryOfficeState(id, request.getName());
        return new RegistryOfficeStateAlterationResponse(updatedState);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RegistryOfficeStateDeletionResponse deleteRegistryOfficeState(@PathVariable String id)
            throws InvalidIdForUpdateException, EntryStillBeingUsedException {
        boolean cantBeDeleted = registryOfficeService.checkifAnyRegistryOfficeContainsState(id);
        if (cantBeDeleted) {
            throw new EntryStillBeingUsedException("Registro utilizado em outro cadastro.");
        }
        registryOfficeStateService.deleteRegistryOfficeState(id);
        return new RegistryOfficeStateDeletionResponse(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RegistryOfficeStateLookupResponse getRegistryOfficeState(@PathVariable String id)
            throws EntryNotFoundException {
        RegistryOfficeState state = registryOfficeStateService.getRegistryOfficeState(id);
        return new RegistryOfficeStateLookupResponse(state);
    }
}
