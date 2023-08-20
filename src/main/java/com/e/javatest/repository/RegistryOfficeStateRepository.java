package com.e.javatest.repository;

import com.e.javatest.model.RegistryOfficeState;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RegistryOfficeStateRepository extends JpaRepository<RegistryOfficeState, String> {
    @Transactional(readOnly = true)
    Optional<RegistryOfficeState> findById(String id);

    @Transactional(readOnly = true)
    Optional<RegistryOfficeState> findByName(String name);

    @Transactional(readOnly = true)
    List<IdAndNameOnly> findAllProjectedBy(Pageable pageable);
}
