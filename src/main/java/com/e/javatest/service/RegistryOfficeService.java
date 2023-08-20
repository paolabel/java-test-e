package com.e.javatest.service;

import com.e.javatest.model.RegistryOffice;
import com.e.javatest.repository.RegistryOfficeRepository;
import java.util.List;
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
}
