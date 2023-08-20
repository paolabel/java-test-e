package com.e.javatest.service;

import com.e.javatest.exception.DuplicateEntryException;
import com.e.javatest.exception.EntryNotFoundException;
import com.e.javatest.exception.InvalidIdForUpdateException;
import com.e.javatest.model.RegistryOfficeState;
import com.e.javatest.repository.RegistryOfficeStateRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
// @Transactional(rollbackFor = {Exception.class, IllegalArgumentException.class})
public class RegistryOfficeStateService {
    @Autowired RegistryOfficeStateRepository registryOfficeStateRepository;

    public String createRegistryOfficeState(String id, String name)
            throws DuplicateEntryException {
        Optional<RegistryOfficeState> duplicateIdEntry =
                registryOfficeStateRepository.findById(id);
        if (duplicateIdEntry.isPresent()) {
            throw new DuplicateEntryException("Registro já cadastrado");
        }
        Optional<RegistryOfficeState> duplicateNameEntry =
                registryOfficeStateRepository.findByName(name);
        if (duplicateNameEntry.isPresent()) {
            String foundId = duplicateNameEntry.get().getId();
            throw new DuplicateEntryException(
                    "Nome já informado no registro com código " + foundId);
        }
        registryOfficeStateRepository.save(new RegistryOfficeState(id, name));
        return id;
    }

    public RegistryOfficeState getRegistryOfficeState(String id)
            throws EntryNotFoundException {
        Optional<RegistryOfficeState> state =
                registryOfficeStateRepository.findById(id);
        if (state.isEmpty()) {
            throw new EntryNotFoundException(
                    "Situação de cartório com id \\'" + id + "\\' não pôde ser encontrada.");
        }
        return state.get();
    }

    // Criar caso para quando não há alteração?
    public RegistryOfficeState updateRegistryOfficeState(String id, String name)
            throws InvalidIdForUpdateException {
        Optional<RegistryOfficeState> existingState =
                registryOfficeStateRepository.findById(id);
        if (existingState.isEmpty()) {
            throw new InvalidIdForUpdateException(
                    "Situação de cartório com id \\'" + id + "\\' não pôde ser encontrada.");
        }
        RegistryOfficeState updatedState = existingState.get();
        updatedState.setName(name);
        registryOfficeStateRepository.save(updatedState);
        return updatedState;
    }

    public String deleteRegistryOfficeState(String id) throws InvalidIdForUpdateException {
        Optional<RegistryOfficeState> existingState =
                registryOfficeStateRepository.findById(id);
        if (existingState.isEmpty()) {
            throw new InvalidIdForUpdateException(
                    "Situação de cartório com id \\'" + id + "\\' não pôde ser encontrada.");
        }
        registryOfficeStateRepository.deleteById(id);
        return id;
    }
}
