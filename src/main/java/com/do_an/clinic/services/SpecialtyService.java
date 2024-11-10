package com.do_an.clinic.services;

import com.do_an.clinic.dtos.SpecialtyDTO;
import com.do_an.clinic.exceptions.DataNotFoundException;
import com.do_an.clinic.models.Specialty;
import com.do_an.clinic.repository.DoctorRepository;
import com.do_an.clinic.repository.ServiceRepository;
import com.do_an.clinic.repository.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpecialtyService implements ISpecialtyService{
    private final SpecialtyRepository specialtyRepository;
    private final DoctorRepository doctorRepository;
    private final ServiceRepository serviceRepository;

    @Override
    public List<Specialty> getAllSpecialties() {
        return specialtyRepository.findAll();
    }

    @Override
    public int getTotalDoctor(Long specialtyId) {
        return doctorRepository.countBySpecialtyId(specialtyId);
    }

    @Override
    public int getTotalService(Long specialtyId) {
        return serviceRepository.countBySpecialtyId(specialtyId);
    }

    @Override
    public Specialty createSpecailty(SpecialtyDTO specialtyDTO) {
        Optional<Specialty> existingSpecialty = specialtyRepository.findByName(specialtyDTO.getName());

        if (existingSpecialty.isPresent()) {
            // Nếu đã tồn tại, ném lỗi hoặc trả về thông báo thích hợp
            throw new RuntimeException("Chuyên ngành có tên'" + specialtyDTO.getName() + "' đã tồn tại.");
        }
        Specialty specialty = Specialty.builder()
                .name(specialtyDTO.getName())
                .build();
        return specialtyRepository.save(specialty);
    }

    @Override
    public Specialty updateSpecialty(Long specialtyId, SpecialtyDTO specialtyDTO) {
        Specialty existingSpecialty = specialtyRepository.findById(specialtyId)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy chuyên khoa có id: " + specialtyId));
        existingSpecialty.setName(specialtyDTO.getName());
        return specialtyRepository.save(existingSpecialty);
    }

    @Override
    public void deleteSpecialtyById(Long specialtyId) {
        doctorRepository.deleteDoctorsBySpecialtyId(specialtyId);
        serviceRepository.deleteServiceBySpecialtyId(specialtyId);
        specialtyRepository.deleteById(specialtyId);
    }

}
