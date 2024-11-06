package com.do_an.clinic.services;

import com.do_an.clinic.models.TimeSlot;
import com.do_an.clinic.repository.TimeSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeSlotService implements ITimeSlotService{
    private final TimeSlotRepository timeSlotRepository;

    @Override
    public List<TimeSlot> getAllTimeSlot() {
        return timeSlotRepository.findAll();
    }
}
