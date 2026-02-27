package com.wsei.healthcare.backend.domain.user;

import com.wsei.healthcare.backend.api.auth.RegisterRequest;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public AppUser toEntity(RegisterRequest registerRequest) {
        AppUser entity = new AppUser();
        entity.setEmail(registerRequest.email());
        entity.setPassword(registerRequest.password());
        return entity;
    }
}
