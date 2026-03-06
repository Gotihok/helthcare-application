package com.wsei.healthcare.backend.to_move;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findAppUserByEmail(@NonNull String email);

    boolean existsAppUserByEmail(@NonNull String email);
}
