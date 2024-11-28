package com.do_an.clinic.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceUsageDTO {
    private Long totalServicesUsed;
    private Long date;
}