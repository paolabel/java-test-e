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
    @Autowired RegistryOfficeStateRepository repository;

    public RegistryOfficeState createRegistryOfficeState(String id, String name)
            throws DuplicateEntryException {
        Optional<RegistryOfficeState> duplicateIdEntry = repository.findById(id);
        if (duplicateIdEntry.isPresent()) {
            throw new DuplicateEntryException("Registro já cadastrado");
        }
        Optional<RegistryOfficeState> duplicateNameEntry = repository.findByName(name);
        if (duplicateNameEntry.isPresent()) {
            String foundId = duplicateNameEntry.get().getId();
            throw new DuplicateEntryException(
                    "Nome já informado no registro com código " + foundId);
        }
        return repository.save(new RegistryOfficeState(id, name));
    }

    public RegistryOfficeState getRegistryOfficeState(String id) throws EntryNotFoundException {
        Optional<RegistryOfficeState> state = repository.findById(id);
        if (state.isEmpty()) {
            throw new EntryNotFoundException(
                    "Situação de cartório com id '" + id + "' não pôde ser encontrada.");
        }
        return state.get();
    }

    public RegistryOfficeState updateRegistryOfficeState(String id, String newName)
            throws InvalidIdForUpdateException {
        Optional<RegistryOfficeState> existingState = repository.findById(id);
        if (existingState.isEmpty()) {
            throw new InvalidIdForUpdateException(
                    "Situação de cartório com id '" + id + "' não pôde ser encontrada.");
        }
        RegistryOfficeState updatedState = existingState.get();
        updatedState.setName(newName);
        return repository.save(updatedState);
    }

    public String deleteRegistryOfficeState(String id) throws InvalidIdForUpdateException {
        Optional<RegistryOfficeState> existingState = repository.findById(id);
        if (existingState.isEmpty()) {
            throw new InvalidIdForUpdateException(
                    "Situação de cartório com id '" + id + "' não pôde ser encontrada.");
        }
        repository.deleteById(id);
        return id;
    }
}
