package com.wsei.healthcare.backend.user.application;

import com.wsei.healthcare.backend.user.api.UserEmailChangedEvent;
import com.wsei.healthcare.backend.user.api.UserEmailUpdateRequest;
import com.wsei.healthcare.backend.user.api.UserRegisterRequest;
import com.wsei.healthcare.backend.user.api.UserResponse;
import com.wsei.healthcare.backend.user.domain.AppAuthPort;
import com.wsei.healthcare.backend.user.domain.AppUser;
import com.wsei.healthcare.backend.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ApplicationEventPublisher eventPublisher;
    private final AppAuthPort authPort;

    //TODO: make transactional
    @Override
    public UserResponse register(UserRegisterRequest request) {
        if (userRepository.existsAppUserByEmail(request.email())) {
            throw new UserAlreadyExistsException("User with this email already exists");
        }

        AppUser appUser = userMapper.toDomain(request);
        AppUser savedUser = userRepository.save(appUser);

        authPort.createAuthIdentity(request, savedUser.getId());
        return userMapper.toDto(savedUser);
    }

    @Override
    public UserResponse updateEmail(UserEmailUpdateRequest request) {
        AppUser user = userRepository.findByEmail(request.oldEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setEmail(request.newEmail());

        AppUser saved = userRepository.save(user);

        eventPublisher.publishEvent(
                new UserEmailChangedEvent(
                        this,
                        saved.getId(),
                        request.newEmail()
                )
        );

        return userMapper.toDto(saved);
    }
}
