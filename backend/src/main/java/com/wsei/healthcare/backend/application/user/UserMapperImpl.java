package com.wsei.healthcare.backend.application.user;

import com.wsei.healthcare.backend.domain.user.AppUser;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    public AppUser toEntity(CreateUserCommand createUserCommand) {
        return new AppUser()
                .setEmail(createUserCommand.email())
                .setPassword(createUserCommand.password())
                .setEnabled(true)
                .setAccountNonLocked(true);
    }
}
