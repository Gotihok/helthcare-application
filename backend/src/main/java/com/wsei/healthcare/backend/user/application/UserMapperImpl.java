package com.wsei.healthcare.backend.user.application;

import com.wsei.healthcare.backend.user.api.UserCreateRequest;
import com.wsei.healthcare.backend.user.domain.AppUser;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public AppUser toDomain(UserCreateRequest userCreateRequest) {
        return new AppUser()
                .setEmail(userCreateRequest.email())
                .setFirstName(userCreateRequest.firstName())
                .setLastName(userCreateRequest.lastName());
//                .setPhoneNumber(userCreateRequest)
//                .setDateOfBirth();
    }
}
