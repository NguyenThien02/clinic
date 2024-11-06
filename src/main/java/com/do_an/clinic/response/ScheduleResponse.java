package com.do_an.clinic.response;


import com.do_an.clinic.models.Schedule;
import com.do_an.clinic.models.TimeSlot;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("user_name")
    private String UserName;

    @JsonProperty("user_phone")
    private String UserPhone;

    @JsonProperty("user_response")
    private UserResponse userResponse;

    @JsonProperty("doctor_response")
    private DoctorResponse doctorResponse;

    @JsonProperty("date")
    private Date date;

    @JsonProperty("time_slot")
    private TimeSlot timeSlot;



    public static ScheduleResponse fromSchedule(Schedule schedule){
        return ScheduleResponse.builder()
                .id(schedule.getId())
                .UserName(schedule.getUserName())
                .UserPhone(schedule.getUserPhone())
                .userResponse(UserResponse.fromUser(schedule.getUser()))
                .doctorResponse(DoctorResponse.fromDoctor(schedule.getDoctor()))
                .date(schedule.getDate())
                .timeSlot(schedule.getTimeSlot())
                .build();
    }
}