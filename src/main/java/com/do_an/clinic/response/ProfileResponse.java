package com.do_an.clinic.response;

import com.do_an.clinic.models.Profile;
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
public class ProfileResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("schedule_response")
    private ScheduleResponse scheduleResponse;

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

    public static ProfileResponse fromProfile(Profile profile){
        return ProfileResponse.builder()
                .id(profile.getId())
                .scheduleResponse(ScheduleResponse.fromSchedule(profile.getSchedule()))
                .diagnosis(profile.getDiagnosis())
                .treatment(profile.getTreatment())
                .medications(profile.getMedications())
                .totalMoney(profile.getTotalMoney())
                .totalInsuranceMoney(profile.getTotalInsuranceMoney())
                .build();
    }
}
