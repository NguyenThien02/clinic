package com.do_an.clinic.controller;

import com.do_an.clinic.models.Specialty;
import com.do_an.clinic.services.ISpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/specialties")
@RequiredArgsConstructor
public class SpecialtyController {
    private final ISpecialtyService specialtyService;

    @GetMapping("")
    public ResponseEntity<?> getAllSpecialties(){
        List<Specialty> specialtyList = specialtyService.getAllSpecialties();
        return ResponseEntity.ok(specialtyList);
    }
}
