package com.deukyun.realworld.user.application.service;

import com.deukyun.realworld.profile.application.port.in.InsertProfileCommand;
import com.deukyun.realworld.profile.application.port.in.InsertProfileInPort;
import com.deukyun.realworld.user.application.port.in.RegisterUserCommand;
import com.deukyun.realworld.user.application.port.out.InsertUserCommand;
import com.deukyun.realworld.user.application.port.out.InsertUserPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class RegisterUserServiceTest {

    RegisterUserService registerUserService;

    PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
    InsertUserPort insertUserPort = mock(InsertUserPort.class);
    InsertProfileInPort insertProfileInPort = mock(InsertProfileInPort.class);

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

        verify(insertProfileInPort).insertProfile(
                new InsertProfileCommand(
                        userId,
                        "Jacob")
        );
    }
}