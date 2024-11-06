package com.do_an.clinic.controller;

import com.do_an.clinic.dtos.ProfileDetailDTO;
import com.do_an.clinic.models.Profile;
import com.do_an.clinic.response.ProfileResponse;
import com.do_an.clinic.services.ProfileDetailService;
import com.do_an.clinic.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/profileDetails")
@RequiredArgsConstructor
public class ProfileDetailController {

    private final ProfileDetailService profileDetailService;
    private final ProfileService profileService;

    @PostMapping("")
    public ResponseEntity<?> createProfileDetails(@RequestBody ProfileDetailDTO profileDetailDTO) {
        try {
            profileDetailService.createProfileDetails(profileDetailDTO.getProfileId(), profileDetailDTO.getServiceIds());
            Profile profile = profileService.getProfileById(profileDetailDTO.getProfileId());
            ProfileResponse profileResponse = ProfileResponse.fromProfile(profile);
            return ResponseEntity.ok(profileResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
