package com.do_an.clinic.services;

import com.do_an.clinic.dtos.CheckTimeSlotDTO;
import com.do_an.clinic.dtos.ScheduleDTO;
import com.do_an.clinic.exceptions.DataNotFoundException;
import com.do_an.clinic.models.Doctor;
import com.do_an.clinic.models.Schedule;
import com.do_an.clinic.models.TimeSlot;
import com.do_an.clinic.models.User;
import com.do_an.clinic.repository.DoctorRepository;
import com.do_an.clinic.repository.ScheduleRepository;
import com.do_an.clinic.repository.TimeSlotRepository;
import com.do_an.clinic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService implements IScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final TimeSlotRepository timeSlotRepository;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public List<TimeSlot> checkTimeSlot(CheckTimeSlotDTO checkTimeSlotDTO) {
        // Lấy danh sách các timeSlot đã được đặt lịch cho bác sĩ và ngày chỉ định
        List<TimeSlot> bookedTimeSlots = scheduleRepository.findBookedTimeSlotsByDoctorAndDate(checkTimeSlotDTO.getDoctorId(), checkTimeSlotDTO.getDate());
        // Lấy tất cả các timeSlot
        List<TimeSlot> allTimeSlots = timeSlotRepository.findAll();
        // Tìm các timeSlot còn trống bằng cách loại bỏ các timeSlot đã đặt khỏi danh sách tổng
        List<TimeSlot> availableTimeSlots = allTimeSlots.stream().filter(timeSlot -> !bookedTimeSlots.contains(timeSlot)).toList();
        if (availableTimeSlots.isEmpty()) {
            return null;
        }
        return availableTimeSlots;
    }

    @Override
    public Schedule createSchedule(ScheduleDTO scheduleDTO) throws Exception {
        User user = userRepository.findById(scheduleDTO.getUserId())
                .orElseThrow(() ->
                        new DataNotFoundException("Không tìm thấy user với id: " + scheduleDTO.getUserId()));
        Doctor doctor = doctorRepository
                .findById(scheduleDTO.getDoctorId()).orElseThrow(() ->
                        new DataNotFoundException("Không tìm thấy doctor với id: " + scheduleDTO.getDoctorId()));
        TimeSlot timeSlot = timeSlotRepository
                .findById(scheduleDTO.getTimeSlotId()).orElseThrow(() ->
                        new DataNotFoundException("Không tìm thấy timeslot với id: " + scheduleDTO.getTimeSlotId()));
        Schedule newSchedule = Schedule.builder().user(user).userName(scheduleDTO.getUserName()).userPhone(scheduleDTO.getUserPhone()).doctor(doctor).date(scheduleDTO.getDate()).timeSlot(timeSlot).build();
        return scheduleRepository.save(newSchedule);
    }

    @Override
    public Page<Schedule> getScheduleByUserId(Long userId, PageRequest pageRequest) {
        return scheduleRepository.findByUserId(userId, pageRequest);
    }
}
