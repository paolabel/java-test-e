package com.e.javatest.controller;

import com.e.javatest.exception.DuplicateEntryException;
import com.e.javatest.exception.InvalidIdForUpdateException;
import com.e.javatest.model.RegistryOfficeSituation;
import com.e.javatest.request.AlterRegistryOfficeSituationRequest;
import com.e.javatest.request.CreateRegistryOfficeSituationRequest;
import com.e.javatest.response.RegistryOfficeSituationAlterationResponse;
import com.e.javatest.response.RegistryOfficeSituationCreationResponse;
import com.e.javatest.service.RegistryOfficeSituationService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        path = "/situacao-cartorio",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistryOfficeSituationController {
    @Autowired RegistryOfficeSituationService registryOfficeSituationService;

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

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public RegistryOfficeSituationAlterationResponse updateRegistryOfficeSituation(
            @RequestBody @Valid AlterRegistryOfficeSituationRequest request)
            throws InvalidIdForUpdateException {
        RegistryOfficeSituation updatedSituation =
                registryOfficeSituationService.updateRegistryOfficeSituation(
                        request.getId(), request.getName());
        return new RegistryOfficeSituationAlterationResponse(updatedSituation);
    }
}
