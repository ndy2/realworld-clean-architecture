package com.deukyun.realworld.profile.application.service;

import com.deukyun.realworld.profile.application.port.in.GetProfileResult;
import com.deukyun.realworld.profile.application.port.out.FindProfileByUserIdPort;
import com.deukyun.realworld.profile.application.port.out.FindProfileByUserIdResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GetProfileByUserIdServiceTest {

    GetProfileByUserIdService getProfileByUserIdService;

    FindProfileByUserIdPort findProfileByUserIdPort = mock(FindProfileByUserIdPort.class);

    @BeforeEach
    void setUp() {
        when(findProfileByUserIdPort.findByUserId(1L))
                .thenReturn(new FindProfileByUserIdResult(
                        "Jakob",
                        null,
                        null
                ));

        getProfileByUserIdService = new GetProfileByUserIdService(findProfileByUserIdPort);
    }

    @Test
    void 프로필_조회() {
        //given
        long userId = 1L;

        //when
        GetProfileResult response = getProfileByUserIdService.getByUserId(userId);

        //then
        verify(findProfileByUserIdPort).findByUserId(1L);

        assertThat(response)
                .extracting(
                        GetProfileResult::getUsername,
                        GetProfileResult::getBio,
                        GetProfileResult::getImage)
                .containsExactly(
                        "Jakob",
                        null,
                        null
                );
    }
}