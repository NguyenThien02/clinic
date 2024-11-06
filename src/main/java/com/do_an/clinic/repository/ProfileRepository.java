package com.do_an.clinic.repository;

import com.do_an.clinic.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProfileRepository extends JpaRepository<Profile, Long> {
    boolean existsByScheduleId(Long scheduleId);

    @Query("SELECT p FROM Profile p WHERE p.schedule.doctor.id = :doctorId")
    List<Profile> findProfilesByDoctorId(@Param("doctorId") Long doctorId);
}
