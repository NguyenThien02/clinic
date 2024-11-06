package com.do_an.clinic.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {
    @JsonProperty("schedule_id")
    private Long scheduleId;

    @JsonProperty("diagnosis")
    private String diagnosis;

    @JsonProperty("treatment")
    private String treatment;

    @JsonProperty("medications")
    private String medications;

    @JsonProperty("total_money")
    private Float totalMoney;

    @JsonProperty("total_insurance_money")
    private Float totalInsuranceMoney;
}