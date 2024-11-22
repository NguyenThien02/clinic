package com.do_an.clinic.services;

import com.do_an.clinic.dtos.ProfileDTO;
import com.do_an.clinic.exceptions.DataNotFoundException;
import com.do_an.clinic.models.Profile;
import com.do_an.clinic.models.Schedule;
import com.do_an.clinic.repository.ProfileDetailRepository;
import com.do_an.clinic.repository.ProfileRepository;
import com.do_an.clinic.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService implements IProfileService{
    private final ProfileRepository profileRepository;
    private final ScheduleRepository scheduleRepository;
    private final ProfileDetailRepository profileDetailRepository;

    @Override
    public Profile createProfile(ProfileDTO profileDTO) throws Exception{
        if(profileRepository.existsByScheduleId(profileDTO.getScheduleId())){
            throw new DataNotFoundException("Lịch khám này đã được tạo profile");
        }
        Schedule schedule = scheduleRepository.findById(profileDTO.getScheduleId())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy schedule với id: " + profileDTO.getScheduleId()));
        Profile newProfile = Profile.builder()
                .schedule(schedule)
                .diagnosis(profileDTO.getDiagnosis())
                .treatment(profileDTO.getTreatment())
                .medications(profileDTO.getMedications())
                .totalMoney(profileDTO.getTotalMoney())
                .totalInsuranceMoney(profileDTO.getTotalInsuranceMoney())
                .build();
        return profileRepository.save(newProfile);
    }

    @Override
    public Profile getProfileById(Long profileId) throws DataNotFoundException {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy profile có id: " + profileId));
        return profile;
    }

    @Override
    public List<Profile> getProfilesByDoctorId(Long doctorId) {
        return profileRepository.findProfilesByDoctorId(doctorId);
    }

    @Override
    public List<Profile>  getProfilesByuserId(Long userId) {
        return profileRepository.findProfilesByUserId(userId);
    }

    @Override
    public Profile updateProfile(Long profileId, ProfileDTO profileDTO) throws DataNotFoundException {
        Profile existingProfile = profileRepository.findById(profileId)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy profile có id: " + profileId));
        existingProfile.setDiagnosis(profileDTO.getDiagnosis());
        existingProfile.setTreatment(profileDTO.getTreatment());
        existingProfile.setMedications(profileDTO.getMedications());
        existingProfile.setTotalMoney(profileDTO.getTotalMoney());
        existingProfile.setTotalInsuranceMoney(profileDTO.getTotalInsuranceMoney());
        return profileRepository.save(existingProfile);
    }

    @Override
    @Transactional
    public void deleteProfileById(Long profileId) {
        profileDetailRepository.deleteByProfileId(profileId);
        profileRepository.deleteById(profileId);
    }
}
