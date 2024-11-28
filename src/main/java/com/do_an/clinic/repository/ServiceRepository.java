package com.do_an.clinic.repository;

import com.do_an.clinic.models.Service;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    @Query("SELECT s FROM Service s WHERE (:specialtyId = 0 OR s.specialty.id = :specialtyId)")
    Page<Service> searchServices(@Param("specialtyId") Long specialtyId, Pageable pageable);

    List<Service> findAllByIdIn(List<Long> ids);

    @Query("SELECT COUNT(d) FROM Service d WHERE d.specialty.id = :specialtyId")
    int countBySpecialtyId(@Param("specialtyId") Long specialtyId);

    boolean existsByName(String name);

    @Modifying //cho biết đây là một câu lệnh thay đổi dữ liệu (không phải SELECT).
    @Transactional
    @Query("DELETE FROM Service d WHERE d.specialty.id = :specialtyId")
    void deleteServiceBySpecialtyId(@Param("specialtyId") Long specialtyId);

}
