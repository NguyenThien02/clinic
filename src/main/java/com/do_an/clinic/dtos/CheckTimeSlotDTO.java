package com.do_an.clinic.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class CheckTimeSlotDTO {

    @JsonProperty("doctor_id")
    private Long doctorId;

    @JsonProperty("date")
    private Date date;

}