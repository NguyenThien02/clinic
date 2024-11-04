package com.do_an.clinic.response;

import com.do_an.clinic.models.Service;
import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceListResponse {
    private List<Service> services;
    private int totalPages;
}
