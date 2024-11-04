package com.do_an.clinic.services;

import com.do_an.clinic.models.Specialty;
import com.do_an.clinic.repository.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialtyService implements ISpecialtyService{
    private final SpecialtyRepository specialtyRepository;

    @Override
    public List<Specialty> getAllSpecialties() {
        return specialtyRepository.findAll();
    }
}
