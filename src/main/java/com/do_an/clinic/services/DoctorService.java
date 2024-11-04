package com.do_an.clinic.services;

import com.do_an.clinic.exceptions.DataNotFoundException;
import com.do_an.clinic.models.Doctor;
import com.do_an.clinic.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorService implements IDoctorService{

    private final DoctorRepository doctorRepository;

    @Override
    public Page<Doctor> getAllDoctors(PageRequest pageRequest, Long speciallyId) {
        Page<Doctor> doctorPage;
        doctorPage = doctorRepository.searchDoctors(pageRequest, speciallyId);
        return doctorPage;
    }

    @Override
    public Doctor uploadImageDoctor(Long id, String fileName) throws DataNotFoundException {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy doctor có id= " + id));
        doctor.setImageUrl(fileName);
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor getDoctorById(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy doctor có id= " + doctorId));
        return doctor;
    }
}
