package com.do_an.clinic.repository;

import com.do_an.clinic.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByPhoneNumber(String phoneNumber);

    Optional<User> findByPhoneNumber(String phoneNumber);

    // Tìm ra các user có role_id =2 và không nằm trong bảng doctors
    @Query("SELECT u FROM User u WHERE u.role.id = 2 AND u.id NOT IN (SELECT d.user.id FROM Doctor d)")
    List<User> getUserDoctor();

    Page<User> findAllByRole_Id(Long roleId, Pageable pageable);
}
