package com.wsei.healthcare.backend.auth.infra.persistence;

import com.wsei.healthcare.backend.auth.domain.AuthIdentity;
import com.wsei.healthcare.backend.auth.domain.AuthIdentityRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthIdentityRepositoryImpl implements AuthIdentityRepository {
    private final AuthIdentityJpaRepository repository;

    @Override
    public Optional<AuthIdentity> findAppUserByEmail(@NonNull String email) {
        AuthIdentityEntity entity = repository.findByEmail(email)
                .orElse(null);
//                .orElseThrow(() -> new RuntimeException("AuthIdentityEntity not found"));
        if (entity == null) {
            //TODO: remove
            //TODO: fix that entity is null
            System.out.println("Auth Identity Entity not found");
            return Optional.empty();
        }

        System.out.println("Auth Identity retrieved: " + entity);

        return Optional.of(new AuthIdentity()
                .setId(entity.getId())
                .setEmail(entity.getEmail())
                .setPasswordHash(entity.getPasswordHash())
                .setEnabled(entity.isEnabled())
                .setAccountNonLocked(entity.isNonLocked()));
    }

    @Override
    public void save(AuthIdentity authIdentity) {
        AuthIdentityEntity entity = new AuthIdentityEntity()
                .setEmail(authIdentity.getEmail())
                .setPasswordHash(authIdentity.getPasswordHash())
//                .setUserId(authIdentity.getUserId())
                .setEnabled(authIdentity.isEnabled())
                .setNonLocked(authIdentity.isAccountNonLocked());
        repository.save(entity);
    }
}
