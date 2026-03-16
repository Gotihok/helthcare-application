package com.wsei.healthcare.backend.user.application;

import com.wsei.healthcare.backend.user.api.UserCreateRequest;
import com.wsei.healthcare.backend.user.api.UserEmailChangedEvent;
import com.wsei.healthcare.backend.user.api.UserEmailUpdateRequest;
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
    //TODO: make framework agnostic
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Long createUser(UserCreateRequest request) {
        if (userRepository.existsAppUserByEmail(request.email())) {
            throw new UserAlreadyExistsException("User with this email already exists");
        }

        AppUser appUser = userMapper.toDomain(request);
        AppUser savedUser = userRepository.save(appUser);
        return savedUser.getId();
    }

    @Override
    public Long updateEmail(UserEmailUpdateRequest request) {
        AppUser user = userRepository.findByEmail(request.oldEmail())
                //TODO: change to not found exception
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setEmail(request.newEmail());

        AppUser saved = userRepository.save(user);

        //TODO: remove
        System.out.println("Should publish UserEmailChangedEvent");
        eventPublisher.publishEvent(
                new UserEmailChangedEvent(
                        this,
                        saved.getId(),
                        request.newEmail()
                )
        );

        //TODO: change to be returning dto response
        return saved.getId();
    }
}
