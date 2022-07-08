package com.deukyun.realworld.user.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(of = "id")
public class User {

    private UserId id;

    private Email email;

    private Password password;

    @Getter
    private Long profileId;

    public static class UserId {
        private Long value;
    }
}
