package com.do_an.clinic.repository;

import com.do_an.clinic.models.Schedule;
import com.do_an.clinic.models.TimeSlot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    // Lấy danh sách time slot có trong lịch khám theo doctorId và date
    @Query("SELECT s.timeSlot FROM Schedule s WHERE s.doctor.id = :doctorId AND s.date = :date " )
    List<TimeSlot> findBookedTimeSlotsByDoctorAndDate(
            @Param("doctorId") Long doctorId,
            @Param("date") Date date);

    // Lấy danh sách lịch khám theo userId và lịch khám không nằm trong danh sách profile
    @Query("SELECT s FROM Schedule s LEFT JOIN Profile p ON p.schedule.id = s.id " +
            "WHERE s.user.id = :userId AND p.id IS NULL")
    Page<Schedule> findSchedulesWithoutProfileByUserId(@Param("userId") Long userId, PageRequest pageRequest);

    @Query("SELECT s FROM Schedule s LEFT JOIN Profile p ON p.schedule.id = s.id " +
            "WHERE s.doctor.id = :doctorId AND p.id IS NULL")
    Page<Schedule> findSchedulesWithoutProfileByDoctorId(@Param("doctorId") Long doctorId, PageRequest pageRequest);
}
