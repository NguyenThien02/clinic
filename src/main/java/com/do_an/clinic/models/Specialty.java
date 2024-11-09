package com.do_an.clinic.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "specialties")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
