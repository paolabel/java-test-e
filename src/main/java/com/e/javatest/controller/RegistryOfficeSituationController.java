package com.e.javatest.controller;

import com.e.javatest.exception.DuplicateEntryException;
import com.e.javatest.exception.EntryNotFoundException;
import com.e.javatest.exception.EntrysStillBeingUsedException;
import com.e.javatest.exception.InvalidIdForUpdateException;
import com.e.javatest.model.RegistryOfficeSituation;
import com.e.javatest.request.AlterRegistryOfficeSituationRequest;
import com.e.javatest.request.CreateRegistryOfficeSituationRequest;
import com.e.javatest.response.RegistryOfficeSituationAlterationResponse;
import com.e.javatest.response.RegistryOfficeSituationCreationResponse;
import com.e.javatest.response.RegistryOfficeSituationDeletionResponse;
import com.e.javatest.response.RegistryOfficeSituationLookupResponse;
import com.e.javatest.service.RegistryOfficeService;
import com.e.javatest.service.RegistryOfficeSituationService;
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
public class RegistryOfficeSituationController {
    @Autowired RegistryOfficeSituationService registryOfficeSituationService;
    @Autowired RegistryOfficeService registryOfficeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegistryOfficeSituationCreationResponse createRegistryOfficeSituation(
            @RequestBody @Valid CreateRegistryOfficeSituationRequest request)
            throws DuplicateEntryException {
        String id =
                registryOfficeSituationService.createRegistryOfficeSituation(
                        request.getId(), request.getName());
        return new RegistryOfficeSituationCreationResponse(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RegistryOfficeSituationAlterationResponse updateRegistryOfficeSituation(
            @PathVariable String id,
            @RequestBody @Valid AlterRegistryOfficeSituationRequest request)
            throws InvalidIdForUpdateException {
        RegistryOfficeSituation updatedSituation =
                registryOfficeSituationService.updateRegistryOfficeSituation(id, request.getName());
        return new RegistryOfficeSituationAlterationResponse(updatedSituation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RegistryOfficeSituationDeletionResponse deleteRegistryOfficeSituation(
            @PathVariable String id)
            throws InvalidIdForUpdateException, EntrysStillBeingUsedException {
        boolean cantBeDeleted = registryOfficeService.checkifAnyRegistryOfficeContainsSituation(id);
        if (cantBeDeleted) {
            throw new EntrysStillBeingUsedException("Registro utilizado em outro cadastro.");
        }
        registryOfficeSituationService.deleteRegistryOfficeSituation(id);
        return new RegistryOfficeSituationDeletionResponse(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RegistryOfficeSituationLookupResponse getRegistryOfficeSituation(@PathVariable String id)
            throws EntryNotFoundException {
        RegistryOfficeSituation situation =
                registryOfficeSituationService.getRegistryOfficeSituation(id);
        return new RegistryOfficeSituationLookupResponse(situation);
    }
}
