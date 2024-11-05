package com.do_an.clinic.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {
    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("specialty_id")
    private Long specialtyId;

    @JsonProperty("training_process")
    private String trainingProcess;

    @JsonProperty("job_description")
    private String jobDescription;

    @JsonProperty("image_url")
    private String imageUrl;
}