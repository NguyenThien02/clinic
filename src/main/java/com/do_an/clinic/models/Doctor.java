package com.do_an.clinic.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "doctors")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    @Column(name = "training_process")
    private String trainingProcess;

    @Column(name = "job_description")
    private String jobDescription;

    @Column(name = "image_url")
    private String imageUrl;
}
