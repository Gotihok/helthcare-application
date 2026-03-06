package com.wsei.healthcare.backend.to_move.application.user;

import com.wsei.healthcare.backend.to_move.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserMapperImpl implements UserMapper {
    public UserEntity toEntity(CreateUserCommand createUserCommand) {
        return new UserEntity()
                .setFirstName(createUserCommand.firstName())
                .setLastName(createUserCommand.lastName())
                .setEmail(createUserCommand.email())
                .setPassword(createUserCommand.password())
                //TODO: add proper mapping
                .setDateOfBirth(LocalDate.EPOCH)
                .setPhoneNumber("123456789");
    }
}
