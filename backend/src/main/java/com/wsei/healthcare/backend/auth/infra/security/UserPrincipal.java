package com.wsei.healthcare.backend.auth.infra.security;

import com.wsei.healthcare.backend.auth.domain.AuthIdentity;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class UserPrincipal implements UserDetails {

    private final String email;
    private final String password;
    private final boolean enabled;
    private final boolean accountNonLocked;

    public static UserPrincipal from(AuthIdentity identity) {
        return new UserPrincipal(
                identity.getEmail(),
                identity.getPasswordHash(),
                //TODO: MAKE PROPER MAPPING
                true,
                true
//                identity.isEnabled(),
//                identity.isAccountNonLocked()
        );
    }

    @Override
    public @NullMarked Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @NonNull String getPassword() {
        //TODO: remove
        System.out.println("Getting password: " + this.password);
        return password;
    }

    @Override
    public @NonNull String getUsername() {
        //TODO: remove
        System.out.println("Getting username: " + this.email);
        return email;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }
}
