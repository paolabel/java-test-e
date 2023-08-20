package com.e.javatest.controller;

import com.e.javatest.exception.DuplicateEntryException;
import com.e.javatest.exception.EntryNotFoundException;
import com.e.javatest.exception.InvalidIdException;
import com.e.javatest.exception.NoFieldToUpdateException;
import com.e.javatest.model.RegistryOffice;
import com.e.javatest.model.RegistryOfficeAssignment;
import com.e.javatest.model.RegistryOfficeState;
import com.e.javatest.request.RegistryOfficeCreationRequest;
import com.e.javatest.request.RegistryOfficeUpdateRequest;
import com.e.javatest.response.ListAllResponse;
import com.e.javatest.response.RegistryOfficeCreationResponse;
import com.e.javatest.response.RegistryOfficeDeletionResponse;
import com.e.javatest.response.RegistryOfficeLookupResponse;
import com.e.javatest.response.RegistryOfficeUpdateResponse;
import com.e.javatest.service.RegistryOfficeAssignmentService;
import com.e.javatest.service.RegistryOfficeService;
import com.e.javatest.service.RegistryOfficeStateService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/cartorio", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class RegistryOfficeController {
    @Autowired RegistryOfficeService registryOfficeService;
    @Autowired RegistryOfficeAssignmentService registryOfficeAssignmentService;
    @Autowired RegistryOfficeStateService registryOfficeStateService;

    private static final String MIN_PAGE_NUMBER_MESSAGE = "Número da página deve ser maior que 0";

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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ListAllResponse listAllRegistryOffices(
            @RequestParam(defaultValue = "1") @Min(value = 1, message = MIN_PAGE_NUMBER_MESSAGE)
                    int page) {
        return new ListAllResponse(registryOfficeService.listAllRegistryOfficeIdAndName(page));
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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RegistryOfficeDeletionResponse deleteRegistryOffice(@PathVariable int id)
            throws InvalidIdException {
        int deletedId = registryOfficeService.deleteRegistryOffice(id);
        return new RegistryOfficeDeletionResponse(deletedId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RegistryOfficeLookupResponse getRegistryOffice(@PathVariable int id)
            throws EntryNotFoundException {
        RegistryOffice registryOffice = registryOfficeService.getRegistryOffice(id);
        return new RegistryOfficeLookupResponse(registryOffice);
    }
}
