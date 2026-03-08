package com.wsei.healthcare.backend.application.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

//@ExtendWith(MockitoExtension.class)
//class UserServiceImplTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private PasswordEncoder passwordEncoder;
//
//    @Mock
//    private UserMapper userMapper;
//
//    @InjectMocks
//    private UserServiceImpl userServiceImpl;
//
//    @Test
//    void createUser_shouldCreateUser_whenUserDoesntExist() {
//        // given
//        UserCreateRequest command = CreateUserCommandFactory.getValidDefault();
//        UserEntity mappedUser = UserEntityFactory.getValidDefault();
//        String rawPassword = mappedUser.getPassword();
//        String encodedPassword = "ENCODED_PASSWORD";
//
//        when(userRepository.existsAppUserByEmail(command.email()))
//                .thenReturn(false);
//
//        when(userMapper.toEntity(command))
//                .thenReturn(mappedUser);
//
//        when(passwordEncoder.encode(rawPassword))
//                .thenReturn(encodedPassword);
//
//        // when
//        userServiceImpl.createUser(command);
//
//        // then
//        verify(userRepository).existsAppUserByEmail(command.email());
//        verify(userMapper).toEntity(command);
//        verify(passwordEncoder).encode(rawPassword);
//
//        ArgumentCaptor<UserEntity> captor = ArgumentCaptor.forClass(UserEntity.class);
//        verify(userRepository).save(captor.capture());
//
//        UserEntity savedUser = captor.getValue();
//        assertEquals(encodedPassword, savedUser.getPassword());
//    }
//
//    @Test
//    void createUser_shouldThrowAlreadyExistException_whenUserAlreadyExists() {
//        // given
//        UserCreateRequest command = CreateUserCommandFactory.getValidDefault();
//
//        when(userRepository.existsAppUserByEmail(command.email()))
//                .thenReturn(true);
//
//        // when
//        assertThrows(
//                UserAlreadyExistsException.class,
//                () -> userServiceImpl.createUser(command)
//        );
//
//        // then
//        verify(userRepository).existsAppUserByEmail(command.email());
//        verify(userMapper, never()).toEntity(any());
//        verify(passwordEncoder, never()).encode(any());
//        verify(userRepository, never()).save(any());
//    }
//}