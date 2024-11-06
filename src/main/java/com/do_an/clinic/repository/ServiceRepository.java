package com.do_an.clinic.repository;

import com.do_an.clinic.models.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    @Query("SELECT s FROM Service s WHERE (:specialtyId = 0 OR s.specialty.id = :specialtyId)")
    Page<Service> searchServices(@Param("specialtyId") Long specialtyId, Pageable pageable);

    List<Service> findAllByIdIn(List<Long> ids);
}
