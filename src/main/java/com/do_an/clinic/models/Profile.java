package com.do_an.clinic.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="profiles")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Profile extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @Column(name = "diagnosis")
    private String diagnosis;

    @Column(name = "treatment")
    private String treatment;

    @Column(name = "medications")
    private String medications;

    @Column(name = "total_money")
    private Float totalMoney;

    @Column(name = "total_insurance_money")
    private Float totalInsuranceMoney;

}