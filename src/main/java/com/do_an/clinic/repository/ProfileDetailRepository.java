package com.do_an.clinic.repository;

import com.do_an.clinic.dtos.ServiceUsageDTO;
import com.do_an.clinic.models.ProfileDetail;
import com.do_an.clinic.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileDetailRepository extends JpaRepository<ProfileDetail, Long> {
    void deleteByProfileId(Long profileId);

    @Query("SELECT pd.service FROM ProfileDetail pd WHERE pd.profile.id = :profileId")
    List<Service> findServicesByProfileId(@Param("profileId") Long profileId);

    // tổng số dịch vụ đã sử dụng theo tháng trong ngày
    @Query("SELECT FUNCTION('DAY', s.date) AS day, COUNT(pd.id) AS totalServicesUsed " +
            "FROM ProfileDetail pd " +
            "JOIN pd.profile p " +
            "JOIN p.schedule s " +
            "WHERE pd.service.id = :serviceId " +
            "AND FUNCTION('MONTH', s.date) = :month " +
            "GROUP BY FUNCTION('DAY', s.date) " +
            "ORDER BY day")
    List<Object[]> findServiceUsageByDay(@Param("serviceId") Long serviceId, @Param("month") Long month);

}
