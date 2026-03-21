package com.wsei.healthcare.backend.application.user;

import com.wsei.healthcare.backend.domain.user.AppUser;
import com.wsei.healthcare.backend.domain.user.UserRole;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    public AppUser toEntity(CreateUserCommand createUserCommand) {
        UserRole role = UserRole.valueOf(createUserCommand.role());
        return new AppUser()
                .setFirstName(createUserCommand.firstName())
                .setLastName(createUserCommand.lastName())
                .setEmail(createUserCommand.email())
                .setPassword(createUserCommand.password())
                .setRole(role);
    }
}
