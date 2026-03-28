package com.wsei.healthcare.backend.shared.security;

import com.wsei.healthcare.backend.auth.application.TokenRevocationService;
import com.wsei.healthcare.backend.auth.application.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//TODO: test
//TODO: check if exceptions should be handled separately
//TODO: maybe change to auth entry point when errors are thrown and throw exception where {
//          filterChain.doFilter(request, response);
//          return;
//      } are used
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final TokenRevocationService tokenRevocationService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        String token = tokenService.resolveToken(
                request.getHeader("Authorization"));

        if (token == null
                || tokenRevocationService.isLoggedOut(token)
                || !tokenService.isValid(token)
        ) {
            filterChain.doFilter(request, response);
            return;
        }

        String username = tokenService.getUsername(token);
        UserDetails principal;

        try {
            principal = userDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            filterChain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        principal,
                        token,
                        principal.getAuthorities()
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
