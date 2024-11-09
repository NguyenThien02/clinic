package com.do_an.clinic.controller;

import com.do_an.clinic.dtos.SpecialtyDTO;
import com.do_an.clinic.models.Specialty;
import com.do_an.clinic.response.MessengerResponse;
import com.do_an.clinic.response.SpecialtyDetailResponse;
import com.do_an.clinic.services.ISpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // Lấy ra tổng số bác sĩ và dịch vụ theo chuyên khoa
    @GetMapping("detail/{id}")
    public ResponseEntity<?> getAllSpecialtyDetail(@PathVariable("id") Long id){
        int totalDoctor = specialtyService.getTotalDoctor(id);
        int totalService = specialtyService.getTotalService(id);
        return ResponseEntity.ok(SpecialtyDetailResponse.builder()
                .totalDoctor(totalDoctor)
                .totalService(totalService)
                .build());
    }

    @PostMapping("")
    public ResponseEntity<?> createSpecialty(@RequestBody SpecialtyDTO specialtyDTO){
        Specialty specialty = specialtyService.createSpecailty(specialtyDTO);
        return ResponseEntity.ok(specialty);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSpecailty(@RequestBody SpecialtyDTO specialtyDTO,
                                             @PathVariable("id") Long specialtyId){
        Specialty specialty = specialtyService.updateSpecialty(specialtyId, specialtyDTO);
        return ResponseEntity.ok(specialty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSpecailty(@PathVariable Long id){
        specialtyService.deleteSpecialtyById(id);
        MessengerResponse messengerResponse = new MessengerResponse("Xóa thành công chuyên khoa có id: " + id);
        return ResponseEntity.ok(messengerResponse);
    }
}
