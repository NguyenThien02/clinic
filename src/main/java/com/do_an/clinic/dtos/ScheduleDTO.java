package com.do_an.clinic.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {
    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("user_phone")
    private String userPhone;

    @JsonProperty("doctor_id")
    private Long doctorId;

    @JsonProperty("date")
    private Date date;

    @JsonProperty("time_slot_id")
    private Long timeSlotId;
}
