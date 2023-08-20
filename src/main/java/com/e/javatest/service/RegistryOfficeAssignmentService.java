package com.e.javatest.service;

import com.e.javatest.exception.DuplicateEntryException;
import com.e.javatest.exception.EntryNotFoundException;
import com.e.javatest.exception.InvalidIdForUpdateException;
import com.e.javatest.exception.NoFieldToUpdateException;
import com.e.javatest.model.RegistryOfficeAssignment;
import com.e.javatest.repository.RegistryOfficeAssignmentRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RegistryOfficeAssignmentService {

    @Autowired RegistryOfficeAssignmentRepository repository;

    public RegistryOfficeAssignment createRegistryOfficeAssignment(
            String id, String name, Optional<Boolean> state) throws DuplicateEntryException {
        Optional<RegistryOfficeAssignment> duplicateIdEntry = repository.findById(id);
        if (duplicateIdEntry.isPresent()) {
            throw new DuplicateEntryException("Registro já cadastrado");
        }
        Optional<RegistryOfficeAssignment> duplicateNameEntry = repository.findByName(name);
        if (duplicateNameEntry.isPresent()) {
            String foundId = duplicateNameEntry.get().getId();
            throw new DuplicateEntryException(
                    "Nome já informado no registro com código " + foundId);
        }
        return repository.save(new RegistryOfficeAssignment(id, name, state));
    }

    public RegistryOfficeAssignment getRegistryOfficeAssignment(String id)
            throws EntryNotFoundException {
        Optional<RegistryOfficeAssignment> assignment = repository.findById(id);
        if (assignment.isEmpty()) {
            throw new EntryNotFoundException(
                    "Atribuição de cartório com id '" + id + "' não pôde ser encontrada.");
        }
        return assignment.get();
    }

    public RegistryOfficeAssignment updateRegistryOfficeAssignment(
            String id, Optional<String> newName, Optional<Boolean> newState)
            throws InvalidIdForUpdateException, NoFieldToUpdateException {
        Optional<RegistryOfficeAssignment> existingAssignment = repository.findById(id);
        if (existingAssignment.isEmpty()) {
            throw new InvalidIdForUpdateException(
                    "Atribuição de cartório com id '" + id + "' não pôde ser encontrada.");
        }
        RegistryOfficeAssignment updatedAssignment = existingAssignment.get();
        boolean wasUpdated = false;
        if (newName.isPresent()) {
            updatedAssignment.setName(newName.get());
            wasUpdated = true;
        }
        if (newState.isPresent()) {
            updatedAssignment.setState(newState.get());
            wasUpdated = true;
        }
        if (!wasUpdated) {
            throw new NoFieldToUpdateException(
                    "Registro não pôde ser atualizado pois não foram enviados campos para"
                            + " alterar.");
        }
        return repository.save(updatedAssignment);
    }
}
