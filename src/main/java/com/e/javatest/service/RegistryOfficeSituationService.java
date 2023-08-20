package com.e.javatest.service;

import com.e.javatest.exception.DuplicateEntryException;
import com.e.javatest.exception.EntryNotFoundException;
import com.e.javatest.exception.InvalidIdForUpdateException;
import com.e.javatest.model.RegistryOfficeSituation;
import com.e.javatest.repository.RegistryOfficeSituationRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
// @Transactional(rollbackFor = {Exception.class, IllegalArgumentException.class})
public class RegistryOfficeSituationService {
    @Autowired RegistryOfficeSituationRepository registryOfficeSituationRepository;

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

    public RegistryOfficeSituation getRegistryOfficeSituation(String id)
            throws EntryNotFoundException {
        Optional<RegistryOfficeSituation> situation =
                registryOfficeSituationRepository.findById(id);
        if (situation.isEmpty()) {
            throw new EntryNotFoundException(
                    "Situação de cartório com id \\'" + id + "\\' não pôde ser encontrada.");
        }
        return situation.get();
    }

    // Criar caso para quando não há alteração?
    public RegistryOfficeSituation updateRegistryOfficeSituation(String id, String name)
            throws InvalidIdForUpdateException {
        Optional<RegistryOfficeSituation> existingSituation =
                registryOfficeSituationRepository.findById(id);
        if (existingSituation.isEmpty()) {
            throw new InvalidIdForUpdateException(
                    "Situação de cartório com id \\'" + id + "\\' não pôde ser encontrada.");
        }
        RegistryOfficeSituation updatedSituation = existingSituation.get();
        updatedSituation.setName(name);
        registryOfficeSituationRepository.save(updatedSituation);
        return updatedSituation;
    }

    public String deleteRegistryOfficeSituation(String id) throws InvalidIdForUpdateException {
        Optional<RegistryOfficeSituation> existingSituation =
                registryOfficeSituationRepository.findById(id);
        if (existingSituation.isEmpty()) {
            throw new InvalidIdForUpdateException(
                    "Situação de cartório com id \\'" + id + "\\' não pôde ser encontrada.");
        }
        registryOfficeSituationRepository.deleteById(id);
        return id;
    }
}
