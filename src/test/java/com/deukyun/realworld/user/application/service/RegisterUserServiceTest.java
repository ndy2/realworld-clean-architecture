package com.deukyun.realworld.user.application.service;

import com.deukyun.realworld.profile.application.port.in.RegisterProfileUseCase;
import com.deukyun.realworld.profile.application.port.in.dto.command.RegisterProfileCommand;
import com.deukyun.realworld.user.application.port.in.dto.command.RegisterUserCommand;
import com.deukyun.realworld.user.application.port.out.InsertUserPort;
import com.deukyun.realworld.user.application.port.out.dto.command.InsertUserCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class RegisterUserServiceTest {

    RegisterUserService registerUserService;

    PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
    InsertUserPort insertUserPort = mock(InsertUserPort.class);
    RegisterProfileUseCase insertProfileInPort = mock(RegisterProfileUseCase.class);

    @BeforeEach
    void setUp() {
        registerUserService = new RegisterUserService(
                passwordEncoder,
                insertUserPort,
                insertProfileInPort
        );
    }

    @Test
    void 회원_가입() {
        //given
        RegisterUserCommand command =
                new RegisterUserCommand(
                        "jake@jake.jake",
                        "jakejake",
                        "Jacob"
                );

        String encodedPassword = passwordEncoder.encode("jakejake");

        //when
        registerUserService.registerUser(command);

        //then
        long userId = verify(insertUserPort).insertUser(
                new InsertUserCommand(
                        "jake@jake.jake",
                        encodedPassword
                )
        );

        verify(insertProfileInPort).registerProfile(
                new RegisterProfileCommand(
                        userId,
                        "Jacob")
        );
    }
}