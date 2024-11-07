package com.do_an.clinic.controller;

import com.do_an.clinic.dtos.ProfileDTO;
import com.do_an.clinic.models.Profile;
import com.do_an.clinic.models.Schedule;
import com.do_an.clinic.response.*;
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

    // Tạo hồ sơ mới
    @PostMapping("")
    public ResponseEntity<?> createProfile(@RequestBody ProfileDTO profileDTO) {
        try {
            Profile profile = profileService.createProfile(profileDTO);
            ProfileResponse profileResponse = ProfileResponse.fromProfile(profile);
            return ResponseEntity.ok(profileResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Cập nhật hồ sơ
    @PutMapping("/{profile_id}")
    public ResponseEntity<?> updateProfileFromDoctor(
            @PathVariable("profile_id") Long profileId,
            @RequestBody ProfileDTO profileDTO){
        try {
            Profile profile =  profileService.updateProfile(profileId, profileDTO);
            ProfileResponse profileResponse = ProfileResponse.fromProfile(profile);
            return ResponseEntity.ok(profileResponse);
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

    // Lấy ra danh sách profile theo userId
    @GetMapping("/user/{user_id}")
    public ResponseEntity<?> getProfilesByuserId(
            @PathVariable("user_id") Long userId) {

        List<Profile> profiles = profileService.getProfilesByuserId(userId);
        List<ProfileResponse> profileResponses = profiles.stream()
                .map(ProfileResponse::fromProfile)
                .toList();
        return ResponseEntity.ok(profileResponses);
    }

    // Lấy ra profile có id
    @GetMapping("/{id}")
    public ResponseEntity<?> getProfileById(@PathVariable("id") Long profileId){
        try {
            Profile profile = profileService.getProfileById(profileId);
            ProfileResponse profileResponse = ProfileResponse.fromProfile(profile);
            return ResponseEntity.ok(profileResponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Xóa profile có profile_id
    @DeleteMapping("/{profile_id}")
    public ResponseEntity<?> deleteProfileById(@PathVariable("profile_id") Long profileId){
        profileService.deleteProfileById(profileId);
        MessengerResponse messenger = new MessengerResponse("Xóa profile thành công với id: " + profileId);
        return ResponseEntity.ok(messenger);
    }
}