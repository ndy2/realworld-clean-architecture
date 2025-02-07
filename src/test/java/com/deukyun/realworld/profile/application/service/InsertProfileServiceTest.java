package com.deukyun.realworld.profile.application.service;

import com.deukyun.realworld.profile.application.port.in.dto.command.RegisterProfileCommand;
import com.deukyun.realworld.profile.application.port.out.InsertProfilePort;
import com.deukyun.realworld.profile.application.port.out.dto.command.InsertProfileCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class InsertProfileServiceTest {


    RegisterProfileService insertProfileService;

    InsertProfilePort insertProfilePort = mock(InsertProfilePort.class);

    @BeforeEach
    void setUp() {
        insertProfileService = new RegisterProfileService(insertProfilePort);
    }

    /**
     * 아웃 포트로 위임만 함
     */
    @Test
    void 프로필_삽입() {
        //given
        RegisterProfileCommand registerProfileCommand = new RegisterProfileCommand(1L, "Jakob");

        //when
        insertProfileService.registerProfile(registerProfileCommand);

        //then
        verify(insertProfilePort)
                .insertProfile(new InsertProfileCommand(1L, "Jakob"));
    }
}