package com.wsei.healthcare.backend.application.user;

import com.wsei.healthcare.backend.domain.user.AppUser;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    public AppUser toEntity(CreateUserCommand createUserCommand) {
        return new AppUser()
                .setFirstName(createUserCommand.firstName())
                .setLastName(createUserCommand.lastName())
                .setEmail(createUserCommand.email())
                .setPassword(createUserCommand.password());
    }
}
