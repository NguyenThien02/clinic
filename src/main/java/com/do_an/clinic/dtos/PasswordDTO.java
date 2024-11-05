package com.do_an.clinic.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordDTO {

    @NotBlank(message = "Password cannot be blank")
    @JsonProperty("password")
    private String password;

    @NotBlank(message = "New password cannot be blank")
    @JsonProperty("new_password")
    private String newPassword;

    @JsonProperty("retype_new_password")
    private String retypeNewPassword;
}
