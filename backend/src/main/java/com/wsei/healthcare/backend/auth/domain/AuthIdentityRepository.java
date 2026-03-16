package com.wsei.healthcare.backend.auth.domain;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthIdentityRepository extends JpaRepository<AuthIdentity, Long> {

    Optional<AuthIdentity> findAuthIdentityByEmail(@NonNull String email);

    Optional<AuthIdentity> findByUserId(Long userId);
}
