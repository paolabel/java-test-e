package com.e.javatest.repository;

import com.e.javatest.model.RegistryOfficeSituation;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RegistryOfficeSituationRepository
        extends JpaRepository<RegistryOfficeSituation, String> {
    @Transactional(readOnly = true)
    Optional<RegistryOfficeSituation> findById(String id);

    @Transactional(readOnly = true)
    Optional<RegistryOfficeSituation> findByName(String name);

    @Transactional(readOnly = true)
    List<RegistryOfficeSituation> findAll();
}
