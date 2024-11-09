package com.do_an.clinic.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SpecialtyDTO {
    @JsonProperty("name")
    private String name;
}
