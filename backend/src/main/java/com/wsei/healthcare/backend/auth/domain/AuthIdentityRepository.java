package com.wsei.healthcare.backend.auth.domain;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthIdentityRepository extends JpaRepository<AuthIdentity, Long> {

    Optional<AuthIdentity> findAppUserByEmail(@NonNull String email);

    Optional<AuthIdentity> findByUserId(Long userId);
}
