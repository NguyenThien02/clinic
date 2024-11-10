package com.do_an.clinic.services;

import com.do_an.clinic.dtos.ServiceDTO;
import com.do_an.clinic.exceptions.DataNotFoundException;
import com.do_an.clinic.models.Service;
import com.do_an.clinic.models.Specialty;
import com.do_an.clinic.repository.ServiceRepository;
import com.do_an.clinic.repository.SpecialtyRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class ServiceService implements IServiceService{
    private final ServiceRepository serviceRepository;
    private final SpecialtyRepository specialtyRepository;

    @Override
    public Page<Service> getAllServices(Long specialtyId, PageRequest pageRequest) {
        Page<Service> servicePage;
        servicePage = serviceRepository.searchServices(specialtyId, pageRequest);
        return servicePage;
    }

    @Override
    public List<Service> findAllByIdIn(List<Long> serviceIds) {
        return serviceRepository.findAllByIdIn(serviceIds);
    }

    @Override
    public Service createService(ServiceDTO serviceDTO) {
        String name = serviceDTO.getName();
        if(serviceRepository.existsByName(name)){
            throw new DataIntegrityViolationException("Tên dịch vụ này đã tồn tại");
        }

        Specialty specialty = specialtyRepository.findById(serviceDTO.getScheduleId())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy chuyên khoa có id: "+ serviceDTO.getScheduleId()) );

        Service newService = Service.builder()
                .specialty(specialty)
                .name(serviceDTO.getName())
                .price(serviceDTO.getPrice())
                .insurancePrice(serviceDTO.getInsurancePrice())
                .build();
        return serviceRepository.save(newService);
    }

    @Override
    @Transactional
    public void deleteServiceById(Long id) {
        serviceRepository.deleteById(id);
    }

    @Override
    public Service updateService(Long id, ServiceDTO serviceDTO) {
        Service exstingService = serviceRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy dịch vụ có Id: " + id ));

        Specialty specialty = specialtyRepository.findById(serviceDTO.getScheduleId())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy chuyên khoa có id: " + id));

        exstingService.setName(serviceDTO.getName());
        exstingService.setSpecialty(specialty);
        exstingService.setPrice(serviceDTO.getPrice());
        exstingService.setInsurancePrice(serviceDTO.getInsurancePrice());
        return serviceRepository.save(exstingService);
    }


}
