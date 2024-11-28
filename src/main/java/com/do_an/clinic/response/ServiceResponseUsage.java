package com.do_an.clinic.response;

import com.do_an.clinic.dtos.ServiceUsageDTO;
import com.do_an.clinic.models.Service;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceResponseUsage {
    private Service service;
    List<ServiceUsageDTO> statistics;
}
