package com.wsei.healthcare.backend.domain.user;

import com.wsei.healthcare.backend.api.auth.UserRegisterRequest;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public AppUser toEntity(UserRegisterRequest registerRequest) {
        AppUser entity = new AppUser();
        entity.setEmail(registerRequest.email());
        entity.setPassword(registerRequest.password());
        return entity;
    }
}
