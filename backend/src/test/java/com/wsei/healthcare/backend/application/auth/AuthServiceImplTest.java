package com.wsei.healthcare.backend.application.auth;

import com.wsei.healthcare.backend.api.auth.JwtResponse;
import com.wsei.healthcare.backend.api.auth.LoginRequest;
import com.wsei.healthcare.backend.api.auth.LogoutRequest;
import com.wsei.healthcare.backend.api.auth.RegisterRequest;
import com.wsei.healthcare.backend.application.token.JwtTokenService;
import com.wsei.healthcare.backend.application.token.TokenRevocationService;
import com.wsei.healthcare.backend.application.user.CreateUserCommand;
import com.wsei.healthcare.backend.application.user.UserService;
import com.wsei.healthcare.backend.infra.security.Jwt;
import com.wsei.healthcare.backend.util.auth.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest implements AuthConstants {

    @Mock
    private UserService userService;

    @Mock
    private JwtTokenService jwtTokenService;

    @Mock
    private TokenRevocationService tokenRevocationService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private AuthMapper authMapper;

    @InjectMocks
    private AuthServiceImpl authService;

    // REGISTER

    @Test
    void register_shouldCreateUserAndReturnJwt_whenValidRequest() {
        // given
        RegisterRequest request = RegisterRequestBuilder.getValidDefault().build();
        CreateUserCommand createUserCommand = authMapper.toCreateUserCommand(request);
        Jwt jwtStub = new Jwt()
                .setJwt(JWT_TOKEN_STUB)
                .setExpiresAt(JWT_EXPIRATION_STUB);
        JwtResponse expectedResponse = JwtResponseFactory.from(jwtStub);

        // auth manager mock
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()
        );
        when(authenticationManager.authenticate(any()))
                .thenReturn(authentication);

        // jwt service mock
        when(jwtTokenService.generateToken(authentication.getName()))
                .thenReturn(jwtStub);

        // when
        JwtResponse actualResponse = authService.register(request);

        // then
        verify(userService).createUser(eq(createUserCommand));
        verify(authenticationManager).authenticate(any());
        verify(jwtTokenService).generateToken(authentication.getName());

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void register_shouldPropagateExceptionAndNotAuthenticate_whenUserCreationFails() {
        // given
        RegisterRequest request = RegisterRequestBuilder.getValidDefault().build();
        CreateUserCommand createUserCommand = authMapper.toCreateUserCommand(request);
        doThrow(new RuntimeException())
                .when(userService).createUser(eq(createUserCommand));

        // when
        assertThrows(
                RuntimeException.class,
                () -> authService.register(request)
        );

        // then
        verify(userService).createUser(any());
        verify(authenticationManager, never()).authenticate(any());
        verify(jwtTokenService, never()).generateToken(any());
    }

    @Test
    void register_shouldPropagateException_whenAuthenticationFails() {
        // given
        RegisterRequest request = RegisterRequestBuilder.getValidDefault().build();
        when(authenticationManager.authenticate(any()))
                .thenThrow(new AuthenticationException("Exception") {});

        // when
        assertThrows(
                AuthenticationException.class,
                () -> authService.register(request)
        );

        // then
        verify(authenticationManager).authenticate(any());
        verify(jwtTokenService, never()).generateToken(any());
    }

    @Test
    void register_shouldPropagateException_whenJwtGenerationFails() {
        // given
        RegisterRequest request = RegisterRequestBuilder.getValidDefault().build();
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()
        );
        when(authenticationManager.authenticate(any()))
                .thenReturn(authentication);
        when(jwtTokenService.generateToken(any()))
                .thenThrow(new RuntimeException());

        // when
        assertThrows(
                RuntimeException.class,
                () -> authService.register(request)
        );

        // then
        verify(jwtTokenService).generateToken(any());
    }

    // LOGIN

    @Test
    void login_shouldReturnJwt_whenValidRequest() {
        // given
        LoginRequest request = LoginRequestBuilder.getValidDefault().build();
        Jwt jwtStub = new Jwt()
                .setJwt(JWT_TOKEN_STUB)
                .setExpiresAt(JWT_EXPIRATION_STUB);
        JwtResponse expectedResponse = JwtResponseFactory.from(jwtStub);

        // auth manager mock
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()
        );
        when(authenticationManager.authenticate(any()))
                .thenReturn(authentication);

        // jwt service mock
        when(jwtTokenService.generateToken(authentication.getName()))
                .thenReturn(jwtStub);

        // when
        JwtResponse actualResponse = authService.login(request);

        // then
        verify(authenticationManager).authenticate(any());
        verify(jwtTokenService).generateToken(authentication.getName());

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void login_shouldPropagateException_whenAuthenticationFails() {
        // given
        LoginRequest request = LoginRequestBuilder.getValidDefault().build();
        when(authenticationManager.authenticate(any()))
                .thenThrow(new AuthenticationException("Exception") {});

        // when
        assertThrows(
                AuthenticationException.class,
                () -> authService.login(request)
        );

        // then
        verify(authenticationManager).authenticate(any());
        verify(jwtTokenService, never()).generateToken(any());
    }

    @Test
    void login_shouldPropagateException_whenJwtGenerationFails() {
        // given
        LoginRequest request = LoginRequestBuilder.getValidDefault().build();
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()
        );
        when(authenticationManager.authenticate(any()))
                .thenReturn(authentication);
        when(jwtTokenService.generateToken(any()))
                .thenThrow(new RuntimeException());

        // when
        assertThrows(
                RuntimeException.class,
                () -> authService.login(request)
        );

        // then
        verify(jwtTokenService).generateToken(any());
    }

    // LOGOUT

    @Test
    void logout_shouldRegisterTokenRevocation_whenJwtIsValid() {
        // given
        LogoutRequest request = LogoutRequestBuilder.getNoTokenDefault()
                .setJwt(JWT_TOKEN_STUB)
                .build();

        when(jwtTokenService.isValid(JWT_TOKEN_STUB))
                .thenReturn(true);

        // when
        authService.logout(request);

        //then
        verify(jwtTokenService, never()).generateToken(any());
        verify(authenticationManager, never()).authenticate(any());

        verify(jwtTokenService).isValid(JWT_TOKEN_STUB);
        verify(tokenRevocationService).registerLoggedOut(JWT_TOKEN_STUB);
    }

    @Test
    void logout_shouldNotRegisterTokenRevocation_whenJwtIsInvalid() {
        // given
        LogoutRequest request = LogoutRequestBuilder.getNoTokenDefault()
                .setJwt(JWT_TOKEN_STUB)
                .build();

        when(jwtTokenService.isValid(JWT_TOKEN_STUB))
                .thenReturn(false);

        // when
        authService.logout(request);

        //then
        verify(jwtTokenService, never()).generateToken(any());
        verify(authenticationManager, never()).authenticate(any());

        verify(jwtTokenService).isValid(JWT_TOKEN_STUB);
        verify(tokenRevocationService, never()).registerLoggedOut(any());
    }

    @Test
    void logout_shouldPropagateException_whenTokenRevocationServiceFails() {
        // given
        LogoutRequest request = LogoutRequestBuilder.getNoTokenDefault()
                .setJwt(JWT_TOKEN_STUB)
                .build();

        when(jwtTokenService.isValid(JWT_TOKEN_STUB))
                .thenReturn(true);

        doThrow(new RuntimeException())
                .when(tokenRevocationService).registerLoggedOut(JWT_TOKEN_STUB);

        // when
        assertThrows(
                RuntimeException.class,
                () -> authService.logout(request)
        );

        //then
        verify(jwtTokenService, never()).generateToken(any());
        verify(authenticationManager, never()).authenticate(any());

        verify(jwtTokenService).isValid(JWT_TOKEN_STUB);
        verify(tokenRevocationService).registerLoggedOut(JWT_TOKEN_STUB);
    }
}