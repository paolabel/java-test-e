package com.e.javatest.service;

import com.e.javatest.exception.DuplicateEntryException;
import com.e.javatest.exception.EntryNotFoundException;
import com.e.javatest.exception.InvalidIdException;
import com.e.javatest.exception.NoFieldToUpdateException;
import com.e.javatest.model.RegistryOffice;
import com.e.javatest.model.RegistryOfficeAssignment;
import com.e.javatest.model.RegistryOfficeState;
import com.e.javatest.repository.RegistryOfficeRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RegistryOfficeService {

    @Autowired RegistryOfficeRepository repository;

    public boolean checkifAnyRegistryOfficeContainsState(String stateId) {
        List<RegistryOffice> list = repository.findByState_Id(stateId);
        return !list.isEmpty();
    }

    public boolean checkifAnyRegistryOfficeContainsAssignment(String assignmentId) {
        List<RegistryOffice> list = repository.findByAssignments_Id(assignmentId);
        return !list.isEmpty();
    }

    public RegistryOffice createRegistryOffice(
            int id,
            String name,
            Optional<String> observation,
            RegistryOfficeState registryOfficeState,
            List<RegistryOfficeAssignment> registryOfficeAssignmentList)
            throws DuplicateEntryException {
        Optional<RegistryOffice> duplicateIdEntry = repository.findById(id);
        if (duplicateIdEntry.isPresent()) {
            throw new DuplicateEntryException("Registro já cadastrado");
        }
        Optional<RegistryOffice> duplicateNameEntry = repository.findByName(name);
        if (duplicateNameEntry.isPresent()) {
            int foundId = duplicateNameEntry.get().getId();
            throw new DuplicateEntryException(
                    "Nome já informado no registro com código " + Integer.toString(foundId));
        }
        return repository.save(
                new RegistryOffice(
                        id, name, observation, registryOfficeState, registryOfficeAssignmentList));
    }

    public RegistryOffice updateRegistryOffice(
            int id,
            Optional<String> newName,
            Optional<String> newObservation,
            Optional<RegistryOfficeState> newState,
            Optional<List<RegistryOfficeAssignment>> newAssignmentList)
            throws InvalidIdException, NoFieldToUpdateException {
        Optional<RegistryOffice> existingRegistryOffice = repository.findById(id);
        if (existingRegistryOffice.isEmpty()) {
            throw new InvalidIdException(
                    "Cartório com id '" + id + "' não existe no banco de dados.");
        }
        RegistryOffice updatedRegistryOffice = existingRegistryOffice.get();
        boolean wasUpdated = false;
        if (newName.isPresent()) {
            updatedRegistryOffice.setName(newName.get());
            wasUpdated = true;
        }
        if (newObservation.isPresent()) {
            updatedRegistryOffice.setObservation(newObservation.get());
            wasUpdated = true;
        }
        if (newState.isPresent()) {
            updatedRegistryOffice.setState(newState.get());
            wasUpdated = true;
        }
        if (newAssignmentList.isPresent()) {
            updatedRegistryOffice.setAssignments(newAssignmentList.get());
            wasUpdated = true;
        }
        if (!wasUpdated) {
            throw new NoFieldToUpdateException(
                    "Registro não pôde ser atualizado pois não foram enviados campos para"
                            + " alterar.");
        }
        return repository.save(updatedRegistryOffice);
    }

    public int deleteRegistryOffice(int id) throws InvalidIdException {
        Optional<RegistryOffice> existingRegistryOffice = repository.findById(id);
        if (existingRegistryOffice.isEmpty()) {
            throw new InvalidIdException(
                    "Cartório com id '" + id + "' não existe no banco de dados.");
        }
        repository.deleteById(id);
        return id;
    }

    public RegistryOffice getRegistryOffice(int id) throws EntryNotFoundException {
        Optional<RegistryOffice> registryOffice = repository.findById(id);
        if (registryOffice.isEmpty()) {
            throw new EntryNotFoundException(
                    "Cartório com id '" + id + "' não pôde ser encontrado.");
        }
        return registryOffice.get();
    }
}
