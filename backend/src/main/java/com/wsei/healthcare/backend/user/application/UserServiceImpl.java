package com.wsei.healthcare.backend.user.application;

import com.wsei.healthcare.backend.user.api.UserCreateRequest;
import com.wsei.healthcare.backend.user.domain.AppUser;
import com.wsei.healthcare.backend.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Long createUser(UserCreateRequest userCreateRequest) {
        if (userRepository.existsAppUserByEmail(userCreateRequest.email())) {
            throw new UserAlreadyExistsException("User with this email already exists");
        }

        AppUser appUser = userMapper.toDomain(userCreateRequest);
        //TODO: remove
        System.out.println("User to save: " + appUser);
        AppUser savedUser = userRepository.save(appUser);
        return savedUser.getId();
    }
}
