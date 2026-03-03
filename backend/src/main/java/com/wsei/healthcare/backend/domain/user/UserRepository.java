package com.wsei.healthcare.backend.domain.user;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findAppUserByEmail(@NonNull String email);

    boolean existsAppUserByEmail(@NonNull String email);
}
