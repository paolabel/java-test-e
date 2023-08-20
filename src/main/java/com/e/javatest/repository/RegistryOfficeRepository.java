package com.e.javatest.repository;

import com.e.javatest.model.RegistryOffice;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RegistryOfficeRepository extends JpaRepository<RegistryOffice, Integer> {
    @Transactional(readOnly = true)
    Optional<RegistryOffice> findById(int id);

    @Transactional(readOnly = true)
    Optional<RegistryOffice> findByName(String name);

    @Transactional(readOnly = true)
    List<RegistryOffice> findByState_Id(String stateId);

    @Transactional(readOnly = true)
    List<RegistryOffice> findByAssignments_Id(String id);

    @Transactional(readOnly = true)
    List<IdAndNameOnly> findAllProjectedBy(Pageable pageable);
}
