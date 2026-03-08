package com.wsei.healthcare.backend.application.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

//@ExtendWith(MockitoExtension.class)
//class AuthServiceImplTest implements AuthConstants {
//
//    @Mock
//    private UserService userService;
//
//    @Mock
//    private TokenService tokenService;
//
//    @Mock
//    private TokenRevocationService tokenRevocationService;
//
//    @Mock
//    private AuthenticationManager authenticationManager;
//
//    @Mock
//    private AuthMapper authMapper;
//
//    @InjectMocks
//    private AuthServiceImpl authService;
//
//    // REGISTER
//
//    @Test
//    void register_shouldCreateUserAndReturnJwt_whenValidRequest() {
//        // given
//        RegisterRequest request = RegisterRequestBuilder.getValidDefault().build();
//        UserCreateRequest createUserCommand = authMapper.toCreateUserCommand(request);
//        Jwt jwtStub = new Jwt()
//                .setJwt(JWT_TOKEN_STUB)
//                .setExpiresAt(JWT_EXPIRATION_STUB);
//        JwtResponse expectedResponse = JwtResponseFactory.from(jwtStub);
//
//        // auth manager mock
//        Authentication authentication = new UsernamePasswordAuthenticationToken(
//                request.email(),
//                request.password()
//        );
//        when(authenticationManager.authenticate(any()))
//                .thenReturn(authentication);
//
//        // jwt service mock
//        when(tokenService.generateToken(authentication.getName()))
//                .thenReturn(jwtStub);
//
//        // when
//        JwtResponse actualResponse = authService.register(request);
//
//        // then
//        verify(userService).createUser(eq(createUserCommand));
//        verify(authenticationManager).authenticate(any());
//        verify(tokenService).generateToken(authentication.getName());
//
//        assertEquals(expectedResponse, actualResponse);
//    }
//
//    @Test
//    void register_shouldPropagateExceptionAndNotAuthenticate_whenUserCreationFails() {
//        // given
//        RegisterRequest request = RegisterRequestBuilder.getValidDefault().build();
//        UserCreateRequest createUserCommand = authMapper.toCreateUserCommand(request);
//        doThrow(new RuntimeException())
//                .when(userService).createUser(eq(createUserCommand));
//
//        // when
//        assertThrows(
//                RuntimeException.class,
//                () -> authService.register(request)
//        );
//
//        // then
//        verify(userService).createUser(any());
//        verify(authenticationManager, never()).authenticate(any());
//        verify(tokenService, never()).generateToken(any());
//    }
//
//    @Test
//    void register_shouldPropagateException_whenAuthenticationFails() {
//        // given
//        RegisterRequest request = RegisterRequestBuilder.getValidDefault().build();
//        when(authenticationManager.authenticate(any()))
//                .thenThrow(new AuthenticationException("Exception") {});
//
//        // when
//        assertThrows(
//                AuthenticationException.class,
//                () -> authService.register(request)
//        );
//
//        // then
//        verify(authenticationManager).authenticate(any());
//        verify(tokenService, never()).generateToken(any());
//    }
//
//    @Test
//    void register_shouldPropagateException_whenJwtGenerationFails() {
//        // given
//        RegisterRequest request = RegisterRequestBuilder.getValidDefault().build();
//        Authentication authentication = new UsernamePasswordAuthenticationToken(
//                request.email(),
//                request.password()
//        );
//        when(authenticationManager.authenticate(any()))
//                .thenReturn(authentication);
//        when(tokenService.generateToken(any()))
//                .thenThrow(new RuntimeException());
//
//        // when
//        assertThrows(
//                RuntimeException.class,
//                () -> authService.register(request)
//        );
//
//        // then
//        verify(tokenService).generateToken(any());
//    }
//
//    // LOGIN
//
//    @Test
//    void login_shouldReturnJwt_whenValidRequest() {
//        // given
//        LoginRequest request = LoginRequestBuilder.getValidDefault().build();
//        Jwt jwtStub = new Jwt()
//                .setJwt(JWT_TOKEN_STUB)
//                .setExpiresAt(JWT_EXPIRATION_STUB);
//        JwtResponse expectedResponse = JwtResponseFactory.from(jwtStub);
//
//        // auth manager mock
//        Authentication authentication = new UsernamePasswordAuthenticationToken(
//                request.email(),
//                request.password()
//        );
//        when(authenticationManager.authenticate(any()))
//                .thenReturn(authentication);
//
//        // jwt service mock
//        when(tokenService.generateToken(authentication.getName()))
//                .thenReturn(jwtStub);
//
//        // when
//        JwtResponse actualResponse = authService.login(request);
//
//        // then
//        verify(authenticationManager).authenticate(any());
//        verify(tokenService).generateToken(authentication.getName());
//
//        assertEquals(expectedResponse, actualResponse);
//    }
//
//    @Test
//    void login_shouldPropagateException_whenAuthenticationFails() {
//        // given
//        LoginRequest request = LoginRequestBuilder.getValidDefault().build();
//        when(authenticationManager.authenticate(any()))
//                .thenThrow(new AuthenticationException("Exception") {});
//
//        // when
//        assertThrows(
//                AuthenticationException.class,
//                () -> authService.login(request)
//        );
//
//        // then
//        verify(authenticationManager).authenticate(any());
//        verify(tokenService, never()).generateToken(any());
//    }
//
//    @Test
//    void login_shouldPropagateException_whenJwtGenerationFails() {
//        // given
//        LoginRequest request = LoginRequestBuilder.getValidDefault().build();
//        Authentication authentication = new UsernamePasswordAuthenticationToken(
//                request.email(),
//                request.password()
//        );
//        when(authenticationManager.authenticate(any()))
//                .thenReturn(authentication);
//        when(tokenService.generateToken(any()))
//                .thenThrow(new RuntimeException());
//
//        // when
//        assertThrows(
//                RuntimeException.class,
//                () -> authService.login(request)
//        );
//
//        // then
//        verify(tokenService).generateToken(any());
//    }
//
//    // LOGOUT
//
//    @Test
//    void logout_shouldRegisterTokenRevocation_whenAuthenticated() {
//        // given
//        LogoutRequest request = LogoutRequestBuilder
//                .getEmptyDefault()
//                .setToken(JWT_TOKEN_STUB)
//                .build();
//
//        // when
//        authService.logout(request);
//
//        //then
//        verify(tokenRevocationService).registerLoggedOut(JWT_TOKEN_STUB);
//    }
//}