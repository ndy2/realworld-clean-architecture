package com.deukyun.realworld.user.application.service;

import com.deukyun.realworld.profile.application.port.in.RegisterProfileCommand;
import com.deukyun.realworld.profile.application.port.in.RegisterProfileUseCase;
import com.deukyun.realworld.user.application.port.in.CustomPasswordEncoder;
import com.deukyun.realworld.user.application.port.in.RegisterUserCommand;
import com.deukyun.realworld.user.application.port.out.InsertUserCommand;
import com.deukyun.realworld.user.application.port.out.InsertUserPort;
import com.deukyun.realworld.user.domain.Email;
import com.deukyun.realworld.user.domain.Password;
import com.deukyun.realworld.user.domain.User.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class RegisterUserServiceTest {

    RegisterUserService registerUserService;
    CustomPasswordEncoder passwordEncoder = mock(CustomPasswordEncoder.class);
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
                        new Email("jake@jake.jake"),
                        new Password("jakejake"),
                        "Jacob"
                );

        Password encodedPassword = passwordEncoder.encode(new Password("jakejake"));

        //when
        registerUserService.registerUser(command);

        //then
        UserId userId = verify(insertUserPort).insertUser(
                new InsertUserCommand(
                        new Email("jake@jake.jake"),
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