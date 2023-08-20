package com.e.javatest.service;

import com.e.javatest.exception.DuplicateEntryException;
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
}
