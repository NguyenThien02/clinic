package com.do_an.clinic.controller;

import com.do_an.clinic.dtos.CheckTimeSlotDTO;
import com.do_an.clinic.dtos.ScheduleDTO;
import com.do_an.clinic.models.Schedule;
import com.do_an.clinic.models.TimeSlot;
import com.do_an.clinic.response.ScheduleListResponse;
import com.do_an.clinic.response.ScheduleResponse;
import com.do_an.clinic.services.IScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final IScheduleService scheduleService;

    @PostMapping("/check_timeSlot")
    public ResponseEntity<?> checkTimeSlot(@RequestBody CheckTimeSlotDTO checkTimeSlotDTO) {
        List<TimeSlot> emptyTimeSlot = scheduleService.checkTimeSlot(checkTimeSlotDTO);
        return ResponseEntity.ok(emptyTimeSlot);
    }

    @PostMapping("")
    public ResponseEntity<?> createSchedule(@RequestBody ScheduleDTO scheduleDTO) throws Exception {
        Schedule schedule = scheduleService.createSchedule(scheduleDTO);
        ScheduleResponse scheduleResponse = ScheduleResponse.fromSchedule(schedule);
        return ResponseEntity.ok(scheduleResponse);
    }

    // lấy ra danh sách lịch khám của user
    @GetMapping("/user/{user_id}")
    public ResponseEntity<?> getScheduleUser(
            @PathVariable("user_id") Long userId,
            @RequestParam("page") int page,
            @RequestParam("limit") int limit) {
        PageRequest pageRequest = PageRequest.of(
                page,
                limit,
                Sort.by("date").ascending()
        );
        Page<Schedule> scchedulePage = scheduleService.getScheduleByUserId(userId, pageRequest);
        Page<ScheduleResponse> scheduleResponsePage = scchedulePage.map(ScheduleResponse::fromSchedule);
        List<ScheduleResponse> scheduleResponses = scheduleResponsePage.getContent();
        int totalPages = scheduleResponsePage.getTotalPages();

        return ResponseEntity.ok(ScheduleListResponse.builder()
                .scheduleResponses(scheduleResponses)
                .totalPages(totalPages)
                .build());
    }
}
