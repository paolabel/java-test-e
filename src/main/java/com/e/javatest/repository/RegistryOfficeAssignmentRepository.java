package com.e.javatest.repository;

import com.e.javatest.model.RegistryOfficeAssignment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RegistryOfficeAssignmentRepository
        extends JpaRepository<RegistryOfficeAssignment, String> {
    @Transactional(readOnly = true)
    Optional<RegistryOfficeAssignment> findById(String id);

    @Transactional(readOnly = true)
    Optional<RegistryOfficeAssignment> findByName(String name);

    @Transactional(readOnly = true)
    List<RegistryOfficeAssignment> findByIdIn(List<String> assignmentIdList);

    @Transactional(readOnly = true)
    List<IdAndNameOnly> findAllProjectedBy(Pageable pageable);
}
