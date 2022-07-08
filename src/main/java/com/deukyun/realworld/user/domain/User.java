package com.deukyun.realworld.user.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;

import static com.google.common.base.Preconditions.checkState;

@EqualsAndHashCode(of = "id")
public class User {

    private UserId id;

    private final Email email;

    private final Password password;

    @Getter
    private Long profileId;

    /**
     * 회원 가입시 사용하는 생성자
     */
    public User(Email email, Password password) {
        this.email = email;
        this.password = password;
    }

    public void assignProfileId(Long profileId){
        checkState(this.profileId == null, "profile id is already assigned");

        this.profileId = profileId;
    }

    @Value
    public static class UserId {
        Long value;
    }
}
