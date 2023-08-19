package com.e.javatest.controller;

import com.e.javatest.exception.DuplicateEntryException;
import com.e.javatest.request.CreateRegistryOfficeSituationRequest;
import com.e.javatest.response.RegistryOfficeSituationCreationResponse;
import com.e.javatest.service.RegistryOfficeSituationService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired RegistryOfficeSituationService registryOfficeSituationService;

    @PostMapping(
            value = "/situacao-cartorio",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegistryOfficeSituationCreationResponse> createRegistryOfficeSituation(
            @RequestBody @Valid CreateRegistryOfficeSituationRequest request)
            throws DuplicateEntryException {
        String id =
                registryOfficeSituationService.createRegistryOfficeSituation(
                        request.getId(), request.getName());
        return new ResponseEntity<>(
                new RegistryOfficeSituationCreationResponse(id), HttpStatus.CREATED);
    }
}
