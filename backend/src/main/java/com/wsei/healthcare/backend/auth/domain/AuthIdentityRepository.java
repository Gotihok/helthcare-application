package com.wsei.healthcare.backend.auth.domain;

import org.jspecify.annotations.NonNull;

import java.util.Optional;

public interface AuthIdentityRepository {

    Optional<AuthIdentity> findAppUserByEmail(@NonNull String email);

    //TODO: make return object
    void save(AuthIdentity authIdentity);
}
