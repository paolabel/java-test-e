package com.e.javatest.service;

import com.e.javatest.exception.DuplicateEntryException;
import com.e.javatest.model.RegistryOfficeSituation;
import com.e.javatest.repository.RegistryOfficeSituationRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegistryOfficeSituationService {
    @Autowired RegistryOfficeSituationRepository registryOfficeSituationRepository;

    // @Transactional(rollbackFor = {Exception.class, IllegalArgumentException.class})
    @Transactional
    public String createRegistryOfficeSituation(String id, String name)
            throws DuplicateEntryException {
        Optional<RegistryOfficeSituation> duplicateIdEntry =
                registryOfficeSituationRepository.findById(id);
        if (duplicateIdEntry.isPresent()) {
            throw new DuplicateEntryException("Registro já cadastrado");
        }
        Optional<RegistryOfficeSituation> duplicateNameEntry =
                registryOfficeSituationRepository.findByName(name);
        if (duplicateNameEntry.isPresent()) {
            String foundId = duplicateNameEntry.get().getId();
            throw new DuplicateEntryException(
                    "Nome já informado no registro com código " + foundId);
        }
        registryOfficeSituationRepository.save(new RegistryOfficeSituation(id, name));
        return id;
    }
}
