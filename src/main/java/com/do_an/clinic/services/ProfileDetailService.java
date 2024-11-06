package com.do_an.clinic.services;

import com.do_an.clinic.exceptions.DataNotFoundException;
import com.do_an.clinic.models.Profile;
import com.do_an.clinic.models.ProfileDetail;
import com.do_an.clinic.models.Service;
import com.do_an.clinic.repository.ProfileDetailRepository;
import com.do_an.clinic.repository.ProfileRepository;
import com.do_an.clinic.repository.ServiceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor

public class ProfileDetailService implements IProfileDetailService{
    private final ProfileDetailRepository profileDetailRepository;
    private final ProfileRepository profileRepository;
    private final ServiceRepository serviceRepository;

    @Override
    @Transactional
    public String createProfileDetails(Long profileId, List<Long> serviceIds) throws DataNotFoundException {
        profileDetailRepository.deleteByProfileId(profileId);
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy Profile có Id: " + profileId));

        for (Long serviceId : serviceIds) {
            Service service = serviceRepository.findById(serviceId)
                    .orElseThrow(() -> new DataNotFoundException("Không tìm thấy dịch vụ có Id: " + serviceId));
            ProfileDetail profileDetail = ProfileDetail.builder()
                    .profile(profile)
                    .service(service)
                    .build();
            profileDetailRepository.save(profileDetail);
        }
        return "Chi tiết hồ sơ đã được tạo thành công";
    }
}
