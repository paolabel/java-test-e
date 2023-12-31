package com.e.javatest.service;

import com.e.javatest.exception.DuplicateEntryException;
import com.e.javatest.exception.EntryNotFoundException;
import com.e.javatest.exception.InvalidIdException;
import com.e.javatest.exception.NoFieldToUpdateException;
import com.e.javatest.model.RegistryOfficeAssignment;
import com.e.javatest.repository.IdAndNameOnly;
import com.e.javatest.repository.RegistryOfficeAssignmentRepository;
import java.util.ArrayList;
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
public class RegistryOfficeAssignmentService {

    @Autowired RegistryOfficeAssignmentRepository repository;

    private static final int PAGE_SIZE = 10;

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
            throw new EntityNotFoundException(
                    "Não existe atribuição de cartório cadastrada com id '" + id + "'.");
        }
        return assignment.get();
    }

    public List<RegistryOfficeAssignment> getAllRegistryOfficeAssignmentsInIdList(
            List<String> idList) throws InvalidIdException {
        List<RegistryOfficeAssignment> assignments = repository.findByIdIn(idList);
        ArrayList<String> foundIdList = new ArrayList<>();
        for (RegistryOfficeAssignment assignment : assignments) {
            foundIdList.add(assignment.getId());
        }
        ArrayList<String> foundIdDifference = new ArrayList<>(idList);
        foundIdDifference.removeAll(foundIdList);
        if (foundIdDifference.size() > 0) {
            throw new InvalidIdException(
                    "Não existe(m) atribuição(ões) de cartório cadastrada(s) com id(s) '"
                            + foundIdDifference
                            + "'.");
        }
        return assignments;
    }

    public RegistryOfficeAssignment updateRegistryOfficeAssignment(
            String id, Optional<String> newName, Optional<Boolean> newState)
            throws EntityNotFoundException, NoFieldToUpdateException, DuplicateEntryException {
        Optional<RegistryOfficeAssignment> existingAssignment = repository.findById(id);
        if (existingAssignment.isEmpty()) {
            throw new EntityNotFoundException(
                    "Não existe atribuição de cartório cadastrada com id '" + id + "'.");
        }
        RegistryOfficeAssignment updatedAssignment = existingAssignment.get();
        boolean wasUpdated = false;
        if (newName.isPresent()) {
            Optional<RegistryOfficeAssignment> duplicateNameEntry =
                    repository.findByName(newName.get());
            if (duplicateNameEntry.isPresent()) {
                String foundId = duplicateNameEntry.get().getId();
                throw new DuplicateEntryException(
                        "Nome já informado no registro com código " + foundId);
            }
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

    public String deleteRegistryOfficeAssignment(String id) throws EntityNotFoundException {
        Optional<RegistryOfficeAssignment> existingAssignment = repository.findById(id);
        if (existingAssignment.isEmpty()) {
            throw new EntityNotFoundException(
                    "Não existe atribuição de cartório cadastrada com id '" + id + "'.");
        }
        repository.deleteById(id);
        return id;
    }

    public List<IdAndNameOnly> listAllRegistryOfficeAssignmentIdAndName(int page) {
        Pageable pageable = PageRequest.of((page - 1), PAGE_SIZE, Sort.by(Order.asc("id")));
        return repository.findAllProjectedBy(pageable);
    }
}
