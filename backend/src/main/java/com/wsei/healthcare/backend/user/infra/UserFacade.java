package com.wsei.healthcare.backend.user.infra;

import com.wsei.healthcare.backend.user.api.UserApi;
import com.wsei.healthcare.backend.user.api.UserEmailUpdateRequest;
import com.wsei.healthcare.backend.user.api.UserRegisterRequest;
import com.wsei.healthcare.backend.user.api.UserResponse;
import com.wsei.healthcare.backend.user.application.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade implements UserApi {
    private final UserService userService;

    @Override
    public UserResponse register(UserRegisterRequest request) {
        return userService.register(request);
    }

    @Override
    public UserResponse updateUserEmail(UserEmailUpdateRequest userEmailUpdateRequest) {
        return userService.updateEmail(userEmailUpdateRequest);
    }
}
