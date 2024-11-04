package com.do_an.clinic.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {

    @JsonProperty("role_id")
    private Long roleId;

    @JsonProperty("token")
    private String token;
}
