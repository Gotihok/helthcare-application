package com.wsei.healthcare.backend.doctor.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByUserId(Long userId);

    boolean existsByUserId(Long userId);
}
