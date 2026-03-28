package com.wsei.healthcare.backend.user.application;

import com.wsei.healthcare.backend.user.api.UserRegisterRequest;
import com.wsei.healthcare.backend.user.api.UserResponse;
import com.wsei.healthcare.backend.user.domain.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public AppUser toDomain(UserRegisterRequest userCreateRequest) {
        return new AppUser()
                .setEmail(userCreateRequest.email())
                .setFirstName(userCreateRequest.firstName())
                .setLastName(userCreateRequest.lastName())
                .setPhoneNumber(userCreateRequest.phoneNumber())
                .setBirthDate(userCreateRequest.birthDate());
    }

    @Override
    public UserResponse toDto(AppUser appUser) {
        log.debug("Mapping user entity to dto: {}", appUser.toString());
        UserResponse dto = new UserResponse(
                appUser.getId(),
                appUser.getEmail(),
                appUser.getFirstName(),
                appUser.getLastName(),
                appUser.getPhoneNumber(),
                appUser.getBirthDate()
        );
        log.debug("Mapped result: {}", dto);
        return dto;
    }
}
