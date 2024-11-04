package com.do_an.clinic.services;

import com.do_an.clinic.models.Service;
import com.do_an.clinic.repository.ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class ServiceService implements IServiceService{
    private final ServiceRepository serviceRepository;

    @Override
    public Page<Service> getAllServices(Long specialtyId, PageRequest pageRequest) {
        Page<Service> servicePage;
        servicePage = serviceRepository.searchServices(specialtyId, pageRequest);
        return servicePage;
    }
}
