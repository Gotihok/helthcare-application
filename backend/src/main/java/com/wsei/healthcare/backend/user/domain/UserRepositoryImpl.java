package com.wsei.healthcare.backend.user.domain;

import com.wsei.healthcare.backend.user.infra.UserEntity;
import com.wsei.healthcare.backend.user.infra.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public boolean existsAppUserByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }

    @Override
    public AppUser save(AppUser appUser) {
        UserEntity entity = new UserEntity()
                .setEmail(appUser.getEmail())
                .setFirstName(appUser.getFirstName())
                .setLastName(appUser.getLastName())
                .setPhoneNumber(appUser.getPhoneNumber())
                .setDateOfBirth(appUser.getDateOfBirth());

        //TODO: make proper return and introduce mapper
        UserEntity savedEntity = userJpaRepository.save(entity);
        return new AppUser()
                .setId(savedEntity.getId())
                .setEmail(savedEntity.getEmail())
                .setFirstName(savedEntity.getFirstName())
                .setLastName(savedEntity.getLastName())
                .setPhoneNumber(savedEntity.getPhoneNumber())
                .setDateOfBirth(savedEntity.getDateOfBirth());
    }
}
