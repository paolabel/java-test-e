package com.e.javatest.service;

import com.e.javatest.exception.DuplicateEntryException;
import com.e.javatest.exception.InvalidIdException;
import com.e.javatest.model.RegistryOfficeState;
import com.e.javatest.repository.IdAndNameOnly;
import com.e.javatest.repository.RegistryOfficeStateRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegistryOfficeStateService {
    @Autowired RegistryOfficeStateRepository repository;

    private static final int PAGE_SIZE = 10;

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

    public Optional<RegistryOfficeState> getRegistryOfficeState(String id) {
        return repository.findById(id);
    }

    public RegistryOfficeState updateRegistryOfficeState(String id, String newName)
            throws InvalidIdException {
        Optional<RegistryOfficeState> existingState = repository.findById(id);
        if (existingState.isEmpty()) {
            throw new InvalidIdException(
                    "Situação de cartório com id '" + id + "' não existe no banco de dados.");
        }
        RegistryOfficeState updatedState = existingState.get();
        updatedState.setName(newName);
        return repository.save(updatedState);
    }

    public String deleteRegistryOfficeState(String id) throws InvalidIdException {
        Optional<RegistryOfficeState> existingState = repository.findById(id);
        if (existingState.isEmpty()) {
            throw new InvalidIdException(
                    "Situação de cartório com id '" + id + "' não existe no banco de dados.");
        }
        repository.deleteById(id);
        return id;
    }

    public List<IdAndNameOnly> listAllRegistryOfficeStateIdAndName(int page) {
        Pageable pageable = PageRequest.of((page - 1), PAGE_SIZE, Sort.by(Order.asc("id")));
        return repository.findAllProjectedBy(pageable);
    }
}
