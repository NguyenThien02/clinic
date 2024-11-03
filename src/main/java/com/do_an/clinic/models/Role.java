package com.do_an.clinic.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 20)
    private String name;

    public static String USER = "USER";
    public static String ADMIN = "ADMIN";
    public static String DOCTOR = "DOCTOR";
}