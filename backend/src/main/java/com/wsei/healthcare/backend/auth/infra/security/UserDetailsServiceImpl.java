package com.wsei.healthcare.backend.auth.infra.security;

import com.wsei.healthcare.backend.auth.domain.AuthIdentity;
import com.wsei.healthcare.backend.auth.domain.AuthIdentityRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AuthIdentityRepository authIdentityRepository;

    @Override
    @NonNull
    public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
        AuthIdentity identity = authIdentityRepository.findAuthIdentityByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return UserPrincipal.from(identity);
    }
}
