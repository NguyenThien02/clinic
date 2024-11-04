package com.do_an.clinic.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="services")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Float price;

    @Column(name = "insurance_price")
    private Float insurancePrice;
}