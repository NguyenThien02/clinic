package com.do_an.clinic.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpecialtyDetailResponse {
    @JsonProperty("total_doctor")
    private int totalDoctor;

    @JsonProperty("total_service")
    private int totalService;
}
