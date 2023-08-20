package com.e.javatest.service;

import com.e.javatest.exception.DuplicateEntryException;
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
}
