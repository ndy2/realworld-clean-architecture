package com.deukyun.realworld.user.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
}