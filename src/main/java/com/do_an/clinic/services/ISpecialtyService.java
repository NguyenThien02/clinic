package com.do_an.clinic.services;

import com.do_an.clinic.dtos.SpecialtyDTO;
import com.do_an.clinic.models.Specialty;

import java.util.List;

public interface ISpecialtyService {
    List<Specialty> getAllSpecialties();

    int getTotalDoctor(Long specialtyId);

    int getTotalService(Long specialtyId);

    Specialty createSpecailty(SpecialtyDTO specialtyDTO);

    Specialty updateSpecialty(Long specialtyId, SpecialtyDTO specialtyDTO);

    void deleteSpecialtyById(Long specialtyId);
}
