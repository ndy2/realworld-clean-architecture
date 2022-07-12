package com.deukyun.realworld.profile.application.service;

import com.deukyun.realworld.profile.application.port.in.GetProfileByUserIdResult;
import com.deukyun.realworld.profile.application.port.out.FindProfileByUserIdPort;
import com.deukyun.realworld.profile.application.port.out.FindProfileByUserIdResult;
import com.deukyun.realworld.profile.domain.Profile;
import com.deukyun.realworld.user.domain.Email;
import com.deukyun.realworld.user.domain.User.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GetProfileByUserIdServiceTest {

    GetProfileByUserIdByUserIdService getProfileByUserIdService;

    FindProfileByUserIdPort findProfileByUserIdPort = mock(FindProfileByUserIdPort.class);

    @BeforeEach
    void setUp() {
        when(findProfileByUserIdPort.findByUserId(new UserId(1L)))
                .thenReturn(new FindProfileByUserIdResult(
                        new Profile.ProfileId(2L),
                        new Email("jake@jake.jake"),
                        "Jakob",
                        null,
                        null
                ));

        getProfileByUserIdService = new GetProfileByUserIdByUserIdService(findProfileByUserIdPort);
    }

    @Test
    void 프로필_조회() {
        //given
        UserId userId = new UserId(1L);

        //when
        GetProfileByUserIdResult response = getProfileByUserIdService.getProfileByUserId(userId);

        //then
        verify(findProfileByUserIdPort).findByUserId(new UserId(1L));

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