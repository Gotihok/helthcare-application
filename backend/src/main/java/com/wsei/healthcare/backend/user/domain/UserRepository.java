package com.wsei.healthcare.backend.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {

    boolean existsAppUserByEmail(String email);

    Optional<AppUser> findByEmail(String s);
}
