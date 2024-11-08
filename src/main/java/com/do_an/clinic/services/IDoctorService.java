package com.do_an.clinic.services;

import com.do_an.clinic.dtos.DoctorDTO;
import com.do_an.clinic.exceptions.DataNotFoundException;
import com.do_an.clinic.models.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IDoctorService {
    Page<Doctor> getAllDoctors(PageRequest pageRequest, Long specialtyId);

    Doctor uploadImageDoctor(Long id, String fileName) throws DataNotFoundException;

    Doctor getDoctorByUserId(Long userId);

    Doctor crateDoctor(DoctorDTO doctorDTO) throws Exception;

    Doctor updateDoctor(Long doctorId, DoctorDTO doctorDTO) throws DataNotFoundException;

    void deleteDoctorById(Long doctorId);
}
