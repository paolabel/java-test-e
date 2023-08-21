package com.e.javatest.service;

import com.e.javatest.exception.DuplicateEntryException;
import com.e.javatest.exception.EntryNotFoundException;
import com.e.javatest.exception.NoFieldToUpdateException;
import com.e.javatest.model.RegistryOffice;
import com.e.javatest.model.RegistryOfficeAssignment;
import com.e.javatest.model.RegistryOfficeState;
import com.e.javatest.repository.IdAndNameOnly;
import com.e.javatest.repository.RegistryOfficeRepository;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RegistryOfficeService {

    private static final int PAGE_SIZE = 10;

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
            throws NoFieldToUpdateException, DuplicateEntryException, EntityNotFoundException {
        Optional<RegistryOffice> existingRegistryOffice = repository.findById(id);
        if (existingRegistryOffice.isEmpty()) {
            throw new EntityNotFoundException(
                    "Não existe cartório cadastrado com id '" + id + "'.");
        }
        RegistryOffice updatedRegistryOffice = existingRegistryOffice.get();
        boolean wasUpdated = false;
        if (newName.isPresent()) {
            Optional<RegistryOffice> duplicateNameEntry = repository.findByName(newName.get());
            if (duplicateNameEntry.isPresent()) {
                int foundId = duplicateNameEntry.get().getId();
                throw new DuplicateEntryException(
                        "Nome já informado no registro com código " + Integer.toString(foundId));
            }
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

    public int deleteRegistryOffice(int id) throws EntityNotFoundException {
        Optional<RegistryOffice> existingRegistryOffice = repository.findById(id);
        if (existingRegistryOffice.isEmpty()) {
            throw new EntityNotFoundException(
                    "Não existe cartório cadastrado com id '" + id + "'.");
        }
        repository.deleteById(id);
        return id;
    }

    public RegistryOffice getRegistryOffice(int id) throws EntryNotFoundException {
        Optional<RegistryOffice> registryOffice = repository.findById(id);
        if (registryOffice.isEmpty()) {
            throw new EntryNotFoundException("Não existe cartório cadastrado com id '" + id + "'.");
        }
        return registryOffice.get();
    }

    public List<IdAndNameOnly> listAllRegistryOfficeIdAndName(int page) {
        Pageable pageable = PageRequest.of((page - 1), PAGE_SIZE, Sort.by(Order.asc("id")));
        return repository.findAllProjectedBy(pageable);
    }
}
