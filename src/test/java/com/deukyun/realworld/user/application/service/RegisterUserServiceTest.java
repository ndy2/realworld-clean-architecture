package com.deukyun.realworld.user.application.service;

import com.deukyun.realworld.profile.application.port.in.InsertProfileInPort;
import com.deukyun.realworld.user.application.port.in.RegisterUserCommand;
import com.deukyun.realworld.user.application.port.out.InsertUserPort;
import com.deukyun.realworld.user.domain.Email;
import com.deukyun.realworld.user.domain.Password;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * 단위 테스트로 유스케이스 테스트하기
 */
class RegisterUserServiceTest {

    RegisterUserService registerUserService;
    InsertUserPort insertUserPort = mock(InsertUserPort.class);
    InsertProfileInPort insertProfileInPort = mock(InsertProfileInPort.class);

    @BeforeEach
    void setUp() {
        registerUserService = new RegisterUserService(
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

        //when
        registerUserService.registerUser(command);

        //then
        verify(insertProfileInPort).insertProfile(
                "Jacob"
        );

        verify(insertUserPort).insertUser(
                new Email("jake@jake.jake"),
                new Password("jakejake")
        );
    }
}