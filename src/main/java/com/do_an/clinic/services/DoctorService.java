package com.do_an.clinic.services;

import com.do_an.clinic.dtos.DoctorDTO;
import com.do_an.clinic.exceptions.DataNotFoundException;
import com.do_an.clinic.exceptions.PermissionDenyException;
import com.do_an.clinic.models.Doctor;
import com.do_an.clinic.models.Role;
import com.do_an.clinic.models.Specialty;
import com.do_an.clinic.models.User;
import com.do_an.clinic.repository.DoctorRepository;
import com.do_an.clinic.repository.SpecialtyRepository;
import com.do_an.clinic.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorService implements IDoctorService{

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final SpecialtyRepository specialtyRepository;

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
    public Doctor getDoctorByUserId(Long userId) {
        Doctor doctor = doctorRepository.findByUserId(userId);
        if (doctor == null || !doctor.getUser().getRole().getName().equals("DOCTOR")) {
            throw new IllegalArgumentException("Người dùng được cung cấp ID không phải là bác sĩ hoặc không tồn tại.");
        }
        return doctor;
    }

    @Override
    @Transactional
    public Doctor crateDoctor(DoctorDTO doctorDTO) throws Exception {
        Long userId = doctorDTO.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy user id = " + userId));

        Long specialtyId = doctorDTO.getSpecialtyId();
        Specialty specialty = specialtyRepository.findById(specialtyId)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy specialty id = " + doctorDTO.getSpecialtyId()));

        if(!user.getRole().getName().equals(Role.DOCTOR)){
            throw new PermissionDenyException("Người dùng này không có vai trò là Bác sĩ");
        }
        Doctor newDoctor = Doctor.builder()
                .jobDescription(doctorDTO.getJobDescription())
                .trainingProcess(doctorDTO.getTrainingProcess())
                .build();
        newDoctor.setSpecialty(specialty);
        newDoctor.setUser(user);
        return doctorRepository.save(newDoctor);
    }

    @Override
    public Doctor updateDoctor(Long doctorId, DoctorDTO doctorDTO) throws DataNotFoundException {
        Doctor existingDoctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy bác sĩ có ID: " + doctorId));

        Specialty specialty = specialtyRepository.findById(doctorDTO.getSpecialtyId())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy chuyên ngành có id:" + doctorDTO.getSpecialtyId()));

        if(doctorDTO.getTrainingProcess().isEmpty()){
            doctorDTO.setTrainingProcess(existingDoctor.getTrainingProcess());
        }
        if(doctorDTO.getJobDescription().isEmpty()){
            doctorDTO.setJobDescription(existingDoctor.getJobDescription());
        }

        existingDoctor.setSpecialty(specialty);
        existingDoctor.setTrainingProcess(doctorDTO.getTrainingProcess());
        existingDoctor.setJobDescription(doctorDTO.getJobDescription());
        return doctorRepository.save(existingDoctor);
    }
}
