package com.e.javatest.repository;

import com.e.javatest.model.RegistryOffice;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RegistryOfficeRepository extends JpaRepository<RegistryOffice, Integer> {
    @Transactional(readOnly = true)
    Optional<RegistryOffice> findById(int id);

    @Transactional(readOnly = true)
    List<RegistryOffice> findByStateId(String stateId);
}
