package com.do_an.clinic.response;

import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleListResponse {
    private List<ScheduleResponse> scheduleResponses;
    private int totalPages;
}
