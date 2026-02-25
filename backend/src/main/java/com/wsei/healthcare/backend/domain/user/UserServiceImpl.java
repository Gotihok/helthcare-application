package com.wsei.healthcare.backend.domain.user;

import com.wsei.healthcare.backend.api.auth.UserRegisterRequest;
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
    public AppUser createUser(UserRegisterRequest registerRequest) {
        AppUser appUser = userMapper.toEntity(registerRequest);
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return userRepository.save(appUser);
    }
}
