package com.wsei.healthcare.backend.application.user;

import com.wsei.healthcare.backend.domain.user.UserEntity;
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
    public void createUser(CreateUserCommand createUserCommand) {
        if (userRepository.existsAppUserByEmail(createUserCommand.email())) {
            throw new UserAlreadyExistsException("User with this email already exists");
        }

        UserEntity userEntity = userMapper.toEntity(createUserCommand);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
    }
}
