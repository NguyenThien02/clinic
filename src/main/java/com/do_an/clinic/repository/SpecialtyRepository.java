package com.do_an.clinic.repository;

import com.do_an.clinic.models.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
    Optional<Specialty> findByName(String name);
}
