package com.deukyun.realworld.user.domain;

import org.junit.jupiter.api.Test;

import static com.deukyun.realworld.common.Fixture.createUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class UserTest {

    @Test
    void 이메일과_비밀번호로_생성() {
        //given
        Email email = new Email("jake@jake.jake");
        Password password = new Password("jakejake");

        //when
        User user = new User(email, password);

        //then
        assertThat(user).isNotNull();
    }

    @Test
    void 프로필_아이디_할당() {
        //given
        Long profileId = 1L;
        User user = createUser();

        //when
        user.assignProfileId(profileId);

        //then
        assertThat(user.getProfileId()).isEqualTo(1L);
    }

    @Test
    void 프로필_아이디_재할당_불가() {
        //given
        Long profileId = 1L;
        User user = createUser();
        user.assignProfileId(profileId);

        //then
        assertThatIllegalStateException()
                .isThrownBy(() -> user.assignProfileId(2L));
    }
}