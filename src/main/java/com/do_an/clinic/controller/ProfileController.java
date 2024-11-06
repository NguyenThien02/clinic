package com.do_an.clinic.controller;

import com.do_an.clinic.dtos.ProfileDTO;
import com.do_an.clinic.models.Profile;
import com.do_an.clinic.models.Schedule;
import com.do_an.clinic.response.ProfileListResponse;
import com.do_an.clinic.response.ProfileResponse;
import com.do_an.clinic.response.ScheduleListResponse;
import com.do_an.clinic.response.ScheduleResponse;
import com.do_an.clinic.services.ProfileService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.prefix}/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("")
    public ResponseEntity<?> createProfile(@RequestBody ProfileDTO profileDTO){
        try {
            Profile profile = profileService.createProfile(profileDTO);
            ProfileResponse profileResponse = ProfileResponse.fromProfile(profile);
            return ResponseEntity.ok(profileResponse);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    // Cập nhật tổng số tiền cho hồ sơ
    @PutMapping("money/{profile_id}")
    public ResponseEntity<?> updateProfileMoney(
            @PathVariable("profile_id") Long profileId,
            @RequestBody ProfileDTO profileDTO){
        try {
            Profile profile =  profileService.updateProfileMoney(profileId, profileDTO);
            return ResponseEntity.ok(profile);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Lấy ra danh sách profile theo doctorId
    @GetMapping("/doctor/{doctor_id}")
    public ResponseEntity<?> getProfilesByDoctorId(
            @PathVariable("doctor_id") Long doctorId) {

        List<Profile> profiles = profileService.getProfilesByDoctorId(doctorId);
        List<ProfileResponse> profileResponses = profiles.stream()
                .map(ProfileResponse::fromProfile)
                .toList();

        return ResponseEntity.ok(profileResponses);
    }
}