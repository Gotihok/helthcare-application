package com.wsei.healthcare.backend.user.infra;

import com.wsei.healthcare.backend.user.api.UserCreateRequest;
import com.wsei.healthcare.backend.user.api.UserApi;
import com.wsei.healthcare.backend.user.api.UserEmailUpdateRequest;
import com.wsei.healthcare.backend.user.application.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade implements UserApi {
    private final UserService userService;

    @Override
    public Long createUser(UserCreateRequest userCreateRequest) {
        return userService.createUser(userCreateRequest);
    }

    @Override
    public Long updateUserEmail(UserEmailUpdateRequest userEmailUpdateRequest) {
        return userService.updateEmail(userEmailUpdateRequest);
    }
}
