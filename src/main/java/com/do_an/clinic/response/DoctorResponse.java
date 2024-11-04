package com.do_an.clinic.response;

import com.do_an.clinic.models.Doctor;
import com.do_an.clinic.models.Specialty;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("user_response")
    private UserResponse userResponse;

    @JsonProperty("specialty")
    private Specialty specialty;

    @JsonProperty("training_process")
    private String trainingProcess;

    @JsonProperty("job_description")
    private String jobDescription;

    @JsonProperty("image_url")
    private String imageUrl;

    public static DoctorResponse fromDoctor(Doctor doctor){
        return DoctorResponse.builder()
                .id(doctor.getId())
                .specialty(doctor.getSpecialty())
                .trainingProcess(doctor.getTrainingProcess())
                .jobDescription(doctor.getJobDescription())
                .imageUrl(doctor.getImageUrl())
                .userResponse(UserResponse.fromUser(doctor.getUser()))
                .build();
    }
}