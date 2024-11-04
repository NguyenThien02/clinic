package com.do_an.clinic.response;

import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorListResponse {
    private List<DoctorResponse> listDoctors;
    private int totalPages;
}
