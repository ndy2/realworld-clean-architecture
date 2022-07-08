package com.deukyun.realworld.profile.application.service;

import com.deukyun.realworld.profile.application.port.in.InsertProfileCommand;
import com.deukyun.realworld.profile.application.port.out.InsertProfileOutCommand;
import com.deukyun.realworld.profile.application.port.out.InsertProfileOutPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class InsertProfileServiceTest {


    InsertProfileService insertProfileService;

    InsertProfileOutPort insertProfileOutPort = mock(InsertProfileOutPort.class);

    @BeforeEach
    void setUp() {
        insertProfileService = new InsertProfileService(insertProfileOutPort);
    }

    /**
     * 아웃 포트로 위임만 함
     */
    @Test
    void 프로필_삽입() {
        //given
        InsertProfileCommand insertProfileCommand = new InsertProfileCommand(1L, "Jakob");

        //when
        insertProfileService.insertProfile(insertProfileCommand);

        //then
        verify(insertProfileOutPort)
                .insertProfile(new InsertProfileOutCommand(1L, "Jakob"));
    }
}