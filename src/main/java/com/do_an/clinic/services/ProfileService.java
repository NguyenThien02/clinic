package com.do_an.clinic.services;

import com.do_an.clinic.dtos.ProfileDTO;
import com.do_an.clinic.exceptions.DataNotFoundException;
import com.do_an.clinic.models.Profile;
import com.do_an.clinic.models.Schedule;
import com.do_an.clinic.repository.ProfileRepository;
import com.do_an.clinic.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService implements IProfileService{
    private final ProfileRepository profileRepository;
    private final ScheduleRepository scheduleRepository;


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
    public Profile updateProfileMoney(Long profileId, ProfileDTO profileDTO) throws DataNotFoundException {
        Profile existingProfile = profileRepository.findById(profileId)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy profile có id: " + profileId));
        existingProfile.setTotalMoney(profileDTO.getTotalMoney());
        existingProfile.setTotalInsuranceMoney(profileDTO.getTotalInsuranceMoney());
        return  profileRepository.save(existingProfile);
    }

    @Override
    public List<Profile> getProfilesByDoctorId(Long doctorId) {
        return profileRepository.findProfilesByDoctorId(doctorId);
    }

}