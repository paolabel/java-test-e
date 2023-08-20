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

    @Autowired RegistryOfficeRepository registryOfficeRepository;

    public boolean checkifAnyRegistryOfficeContainsState(String stateId) {
        List<RegistryOffice> list = registryOfficeRepository.findByStateId(stateId);
        return !list.isEmpty();
    }
}
