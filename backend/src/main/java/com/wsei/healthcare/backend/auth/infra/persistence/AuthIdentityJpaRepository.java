package com.wsei.healthcare.backend.auth.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthIdentityJpaRepository extends JpaRepository<AuthIdentityEntity, Long> {
    Optional<AuthIdentityEntity> findByEmail(String email);
}
