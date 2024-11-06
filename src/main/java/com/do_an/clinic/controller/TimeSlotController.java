package com.do_an.clinic.controller;

import com.do_an.clinic.models.TimeSlot;
import com.do_an.clinic.services.TimeSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/time-slots")
@RequiredArgsConstructor
public class TimeSlotController {
    private final TimeSlotService timeSlotService;

    @GetMapping("")
    public ResponseEntity<?> getAllTimeSlot(){
        List<TimeSlot> timeSlotList = timeSlotService.getAllTimeSlot();
        return ResponseEntity.ok(timeSlotList);
    }
}
