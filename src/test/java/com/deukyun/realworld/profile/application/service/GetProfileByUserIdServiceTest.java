package com.deukyun.realworld.profile.application.service;

import com.deukyun.realworld.profile.application.port.in.GetProfileByUserIdResult;
import com.deukyun.realworld.profile.application.port.out.FindProfileByUserIdPort;
import com.deukyun.realworld.profile.application.port.out.FindProfileByUserIdResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GetProfileByUserIdServiceTest {

    GetProfileByUserIdByUserIdService getProfileByUserIdService;

    FindProfileByUserIdPort findProfileByUserIdPort = mock(FindProfileByUserIdPort.class);

    @BeforeEach
    void setUp() {
        when(findProfileByUserIdPort.findByUserId(1L))
                .thenReturn(new FindProfileByUserIdResult(
                        "Jakob",
                        null,
                        null
                ));

        getProfileByUserIdService = new GetProfileByUserIdByUserIdService(findProfileByUserIdPort);
    }

    @Test
    void 프로필_조회() {
        //given
        long userId = 1L;

        //when
        GetProfileByUserIdResult response = getProfileByUserIdService.getProfileByUserId(userId);

        //then
        verify(findProfileByUserIdPort).findByUserId(1L);

        assertThat(response)
                .extracting(
                        GetProfileByUserIdResult::getUsername,
                        GetProfileByUserIdResult::getBio,
                        GetProfileByUserIdResult::getImage)
                .containsExactly(
                        "Jakob",
                        null,
                        null
                );
    }
}