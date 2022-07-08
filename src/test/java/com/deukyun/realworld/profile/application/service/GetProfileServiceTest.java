package com.deukyun.realworld.profile.application.service;

import com.deukyun.realworld.profile.application.port.in.ProfileInResponse;
import com.deukyun.realworld.profile.application.port.out.FindProfilePort;
import com.deukyun.realworld.profile.application.port.out.ProfileOutResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GetProfileServiceTest {

    GetProfileService getProfileService;

    FindProfilePort findProfilePort = mock(FindProfilePort.class);

    @BeforeEach
    void setUp() {
        when(findProfilePort.findByUserId(1L))
                .thenReturn(new ProfileOutResponse(
                        "Jakob",
                        null,
                        null
                ));

        getProfileService = new GetProfileService(findProfilePort);
    }

    @Test
    void 프로필_조회() {
        //given
        long userId = 1L;

        //when
        ProfileInResponse response = getProfileService.getByUserId(userId);

        //then
        verify(findProfilePort).findByUserId(1L);

        assertThat(response)
                .extracting(
                        ProfileInResponse::getUsername,
                        ProfileInResponse::getBio,
                        ProfileInResponse::getImage)
                .containsExactly(
                        "Jakob",
                        null,
                        null
                );
    }
}