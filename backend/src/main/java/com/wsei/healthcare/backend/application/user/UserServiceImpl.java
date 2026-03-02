package com.wsei.healthcare.backend.application.user;

import com.wsei.healthcare.backend.api.auth.RegisterRequest;
import com.wsei.healthcare.backend.domain.user.AppUser;
import com.wsei.healthcare.backend.domain.user.UserMapper;
import com.wsei.healthcare.backend.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public void createUser(RegisterRequest registerRequest) {
        AppUser appUser = userMapper.toEntity(registerRequest);
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        userRepository.save(appUser);
    }
}
