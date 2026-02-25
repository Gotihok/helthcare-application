package com.wsei.healthcare.backend.domain.user;

import com.wsei.healthcare.backend.api.auth.UserRegisterRequest;

public interface UserService {
    AppUser createUser(UserRegisterRequest registerRequest);
}
