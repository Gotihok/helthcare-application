package com.wsei.healthcare.backend.shared.security;

import com.wsei.healthcare.backend.to_move.UserEntity;
import com.wsei.healthcare.backend.to_move.UserRepository;
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
        UserEntity userEntity = userRepository.findAppUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return UserPrincipal.from(userEntity);
    }
}
