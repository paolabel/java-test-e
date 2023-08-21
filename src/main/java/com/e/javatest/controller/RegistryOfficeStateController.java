package com.e.javatest.controller;

import com.e.javatest.exception.DuplicateEntryException;
import com.e.javatest.exception.EntryNotFoundException;
import com.e.javatest.exception.EntryStillBeingUsedException;
import com.e.javatest.exception.InvalidIdException;
import com.e.javatest.model.RegistryOfficeState;
import com.e.javatest.request.StateCreationRequest;
import com.e.javatest.request.StateUpdateRequest;
import com.e.javatest.response.ListAllResponse;
import com.e.javatest.response.StateCreationResponse;
import com.e.javatest.response.StateDeletionResponse;
import com.e.javatest.response.StateLookupResponse;
import com.e.javatest.response.StateUpdateResponse;
import com.e.javatest.service.RegistryOfficeService;
import com.e.javatest.service.RegistryOfficeStateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Situações de cartório")
@RestController
@RequestMapping(path = "/situacao-cartorio", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistryOfficeStateController {
    @Autowired RegistryOfficeStateService registryOfficeStateService;
    @Autowired RegistryOfficeService registryOfficeService;

    private static final String MIN_PAGE_NUMBER_MESSAGE = "Número da página deve ser maior que 0";

    @Operation(summary = "Cadastra uma nova situação de cartório")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StateCreationResponse createRegistryOfficeState(
            @RequestBody @Valid StateCreationRequest request) throws DuplicateEntryException {
        RegistryOfficeState newRegistryOfficeState =
                registryOfficeStateService.createRegistryOfficeState(
                        request.getId(), request.getName());
        return new StateCreationResponse(newRegistryOfficeState);
    }

    @Operation(summary = "Lista os IDs e nomes de todas as situações de cartório cadastradas")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ListAllResponse listAllRegistryOfficeStates(
            @RequestParam(defaultValue = "1") @Min(value = 1, message = MIN_PAGE_NUMBER_MESSAGE)
                    int page) {
        return new ListAllResponse(
                registryOfficeStateService.listAllRegistryOfficeStateIdAndName(page));
    }

    @Operation(summary = "Atualiza os dados de uma situação de cartório cadastrada")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StateUpdateResponse updateRegistryOfficeState(
            @PathVariable String id, @RequestBody @Valid StateUpdateRequest request)
            throws InvalidIdException {
        RegistryOfficeState updatedState =
                registryOfficeStateService.updateRegistryOfficeState(id, request.getName());
        return new StateUpdateResponse(updatedState);
    }

    @Operation(summary = "Deleta uma situação de cartório cadastrada")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StateDeletionResponse deleteRegistryOfficeState(@PathVariable String id)
            throws InvalidIdException, EntryStillBeingUsedException {
        boolean cantBeDeleted = registryOfficeService.checkifAnyRegistryOfficeContainsState(id);
        if (cantBeDeleted) {
            throw new EntryStillBeingUsedException("Registro utilizado em outro cadastro.");
        }
        String deletedId = registryOfficeStateService.deleteRegistryOfficeState(id);
        return new StateDeletionResponse(deletedId);
    }

    @Operation(summary = "Consulta os dados de uma situação de cartório cadastrada")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StateLookupResponse getRegistryOfficeState(@PathVariable String id)
            throws EntryNotFoundException {
        Optional<RegistryOfficeState> state = registryOfficeStateService.getRegistryOfficeState(id);
        if (state.isEmpty()) {
            throw new EntryNotFoundException(
                    "Situação de cartório com id '" + id + "' não pôde ser encontrada.");
        }
        return new StateLookupResponse(state.get());
    }
}
