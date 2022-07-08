package com.deukyun.realworld.profile.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProfileTest {

    @Test
    void 프로필_생성() {
        //given
        String username = "Jacob";

        //when
        Profile profile = new Profile(username);

        //then
        assertThat(profile).isNotNull();
    }
}