package com.wsei.healthcare.backend.infra.security;

import com.wsei.healthcare.backend.domain.user.AppUser;
import com.wsei.healthcare.backend.domain.user.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @NonNull
    public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
        AppUser user = userRepository.findAppUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new UserPrincipal()
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .setEnabled(user.isEnabled())
                .setAccountNonLocked(user.isAccountNonLocked());
    }
}
