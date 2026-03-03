package com.wsei.healthcare.backend.application.user;

import com.wsei.healthcare.backend.domain.user.AppUser;
import com.wsei.healthcare.backend.domain.user.UserRepository;
import com.wsei.healthcare.backend.util.user.AppUserFactory;
import com.wsei.healthcare.backend.util.user.CreateUserCommandFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    void createUser_shouldCreateUser_whenUserDoesntExist() {
        // given
        CreateUserCommand command = CreateUserCommandFactory.getValidDefault();
        AppUser mappedUser = AppUserFactory.getValidDefault();
        String rawPassword = mappedUser.getPassword();
        String encodedPassword = "ENCODED_PASSWORD";

        when(userRepository.existsAppUserByEmail(command.email()))
                .thenReturn(false);

        when(userMapper.toEntity(command))
                .thenReturn(mappedUser);

        when(passwordEncoder.encode(rawPassword))
                .thenReturn(encodedPassword);

        // when
        userServiceImpl.createUser(command);

        // then
        verify(userRepository).existsAppUserByEmail(command.email());
        verify(userMapper).toEntity(command);
        verify(passwordEncoder).encode(rawPassword);

        ArgumentCaptor<AppUser> captor = ArgumentCaptor.forClass(AppUser.class);
        verify(userRepository).save(captor.capture());

        AppUser savedUser = captor.getValue();
        assertEquals(encodedPassword, savedUser.getPassword());
    }

    @Test
    void createUser_shouldThrowAlreadyExistException_whenUserAlreadyExists() {
        // given
        CreateUserCommand command = CreateUserCommandFactory.getValidDefault();

        when(userRepository.existsAppUserByEmail(command.email()))
                .thenReturn(true);

        // when
        assertThrows(
                UserAlreadyExistsException.class,
                () -> userServiceImpl.createUser(command)
        );

        // then
        verify(userRepository).existsAppUserByEmail(command.email());
        verify(userMapper, never()).toEntity(any());
        verify(passwordEncoder, never()).encode(any());
        verify(userRepository, never()).save(any());
    }
}