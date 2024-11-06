package com.do_an.clinic.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name="profile_details")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

}