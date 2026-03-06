package com.wsei.healthcare.backend.auth.infra;

import com.wsei.healthcare.backend.auth.api.RegisterRequest;
import com.wsei.healthcare.backend.auth.domain.UserAdapter;
import com.wsei.healthcare.backend.to_move.application.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAdapterImpl implements UserAdapter {
    // TODO: refactor when user service changes
    private final AuthMapper authMapper;
    private final UserService userService;

    @Override
    public void createUser(RegisterRequest registerRequest) {
        userService.createUser(
                authMapper.toCreateUserCommand(registerRequest)
        );
    }
}
