package com.do_an.clinic.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDTO {
    @JsonProperty("specialty_id")
    private Long scheduleId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private Float price;

    @JsonProperty("insurance_price")
    private Float insurancePrice;
}