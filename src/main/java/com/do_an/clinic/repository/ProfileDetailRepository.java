package com.do_an.clinic.repository;

import com.do_an.clinic.models.ProfileDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileDetailRepository extends JpaRepository<ProfileDetail, Long> {
    void deleteByProfileId(Long profileId);
}
