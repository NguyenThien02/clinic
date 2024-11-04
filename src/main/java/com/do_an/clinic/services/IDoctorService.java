package com.do_an.clinic.services;

import com.do_an.clinic.exceptions.DataNotFoundException;
import com.do_an.clinic.models.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IDoctorService {
    Page<Doctor> getAllDoctors(PageRequest pageRequest, Long specialtyId);

    Doctor uploadImageDoctor(Long id, String fileName) throws DataNotFoundException;
}
