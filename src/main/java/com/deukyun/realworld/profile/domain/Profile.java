package com.deukyun.realworld.profile.domain;

import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(of = "id")
public class Profile {

    private ProfileId id;

    private final String username;

    private String bio;

    private String image;

    private boolean following;

    //회원 가입시 사용되는 생성자
    public Profile(String username) {
        this.username = username;
    }

    @Value
    public static class ProfileId {
        Long value;
    }
}
