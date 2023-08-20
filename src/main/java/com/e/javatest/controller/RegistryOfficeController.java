package com.e.javatest.controller;

import com.e.javatest.exception.DuplicateEntryException;
import com.e.javatest.exception.InvalidIdException;
import com.e.javatest.exception.NoFieldToUpdateException;
import com.e.javatest.model.RegistryOffice;
import com.e.javatest.model.RegistryOfficeAssignment;
import com.e.javatest.model.RegistryOfficeState;
import com.e.javatest.request.RegistryOfficeCreationRequest;
import com.e.javatest.request.RegistryOfficeUpdateRequest;
import com.e.javatest.response.RegistryOfficeCreationResponse;
import com.e.javatest.response.RegistryOfficeUpdateResponse;
import com.e.javatest.service.RegistryOfficeAssignmentService;
import com.e.javatest.service.RegistryOfficeService;
import com.e.javatest.service.RegistryOfficeStateService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/cartorio", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistryOfficeController {
    @Autowired RegistryOfficeService registryOfficeService;
    @Autowired RegistryOfficeAssignmentService registryOfficeAssignmentService;
    @Autowired RegistryOfficeStateService registryOfficeStateService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegistryOfficeCreationResponse createRegistryOfficeWithIds(
            @RequestBody @Valid RegistryOfficeCreationRequest request)
            throws DuplicateEntryException, InvalidIdException {

        Optional<RegistryOfficeState> state =
                registryOfficeStateService.getRegistryOfficeState(request.getStateId());
        if (state.isEmpty()) {
            throw new InvalidIdException(
                    "Situação de cartório com id '"
                            + request.getStateId()
                            + "' não existe no banco de dados.");
        }
        List<RegistryOfficeAssignment> assignments =
                registryOfficeAssignmentService.getAllRegistryOfficeAssignmentsInIdList(
                        request.getAssignmentIdList());

        RegistryOffice newRegistryOffice =
                registryOfficeService.createRegistryOffice(
                        request.getId(),
                        request.getName(),
                        request.getObservation(),
                        state.get(),
                        assignments);
        return new RegistryOfficeCreationResponse(newRegistryOffice);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RegistryOfficeUpdateResponse updateRegistryOffice(
            @PathVariable int id, @RequestBody @Valid RegistryOfficeUpdateRequest request)
            throws InvalidIdException, NoFieldToUpdateException {

        Optional<RegistryOfficeState> newState = Optional.empty();
        if (request.getStateId().isPresent()) {
            String newStateId = request.getStateId().get();
            newState = registryOfficeStateService.getRegistryOfficeState(newStateId);
            if (newState.isEmpty()) {
                throw new InvalidIdException(
                        "Situação de cartório com id '"
                                + newStateId
                                + "' não existe no banco de dados.");
            }
        }
        Optional<List<RegistryOfficeAssignment>> newAssignmentList = Optional.empty();
        if (request.getAssignmentIdList().isPresent()) {
            List<RegistryOfficeAssignment> assignments =
                    registryOfficeAssignmentService.getAllRegistryOfficeAssignmentsInIdList(
                            request.getAssignmentIdList().get());
            newAssignmentList = Optional.of(assignments);
        }

        RegistryOffice updatedRegistryOffice =
                registryOfficeService.updateRegistryOffice(
                        id,
                        request.getName(),
                        request.getObservation(),
                        newState,
                        newAssignmentList);
        return new RegistryOfficeUpdateResponse(updatedRegistryOffice);
    }
}
