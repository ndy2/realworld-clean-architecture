package com.deukyun.realworld.user.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;

@EqualsAndHashCode(of = "id")
public class User {

    private UserId id;

    private final Email email;

    private final Password password;

    /**
     * 회원 가입시 사용하는 생성자
     */
    public User(Email email, Password password) {
        this.email = email;
        this.password = password;
    }

    @Getter
    private Long profileId;

    @Value
    public static class UserId {
        Long value;
    }
}
