package com.do_an.clinic.services;

import com.do_an.clinic.dtos.ProfileDTO;
import com.do_an.clinic.exceptions.DataNotFoundException;
import com.do_an.clinic.models.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IProfileService {
    Profile createProfile(ProfileDTO profileDTO) throws Exception;

    Profile getProfileById(Long profileId) throws DataNotFoundException;

    List<Profile> getProfilesByDoctorId(Long doctorId);

    List<Profile>  getProfilesByuserId(Long userId);

    Profile updateProfile(Long profileId, ProfileDTO profileDTO) throws DataNotFoundException;
}
