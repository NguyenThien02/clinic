package com.do_an.clinic.services;

import com.do_an.clinic.dtos.ServiceDTO;
import com.do_an.clinic.dtos.ServiceUsageDTO;
import com.do_an.clinic.models.Service;
import com.do_an.clinic.response.ServiceResponseUsage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IServiceService {
    Page<Service> getAllServices(Long specialtyId, PageRequest pageRequest);

    List<Service> findAllByIdIn(List<Long> serviceIds);

    Service createService(ServiceDTO serviceDTO);

    void deleteServiceById(Long id);

    Service updateService(Long id, ServiceDTO serviceDTO);

    Service getService(Long id);

    List<ServiceUsageDTO> getServiceUsageStatistics(Long serviceId, Long month);

}
