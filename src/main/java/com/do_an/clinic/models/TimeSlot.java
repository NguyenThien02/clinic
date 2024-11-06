package com.do_an.clinic.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "time_slots")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_end")
    private String startEnd;

}