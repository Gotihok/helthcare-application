package com.wsei.healthcare.backend.user.domain;

public interface UserRepository {

    boolean existsAppUserByEmail(String email);

    AppUser save(AppUser userEntity);
}
