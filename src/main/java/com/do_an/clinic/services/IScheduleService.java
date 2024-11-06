package com.do_an.clinic.services;

import com.do_an.clinic.dtos.CheckTimeSlotDTO;
import com.do_an.clinic.dtos.ScheduleDTO;
import com.do_an.clinic.models.Schedule;
import com.do_an.clinic.models.TimeSlot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IScheduleService {
    List<TimeSlot> checkTimeSlot(CheckTimeSlotDTO checkTimeSlotDTO);

    Schedule createSchedule(ScheduleDTO scheduleDTO) throws Exception;

    Page<Schedule> getScheduleByUserId(Long userId, PageRequest pageRequest);

    Page<Schedule> getScheduleByDoctorId(Long doctorId, PageRequest pageRequest);
}
