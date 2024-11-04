package com.do_an.clinic.services;

import com.do_an.clinic.models.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IServiceService {
    Page<Service> getAllServices(Long specialtyId, PageRequest pageRequest);
}
