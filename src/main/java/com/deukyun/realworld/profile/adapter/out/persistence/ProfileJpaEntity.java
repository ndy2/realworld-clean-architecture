package com.deukyun.realworld.profile.adapter.out.persistence;

import com.deukyun.realworld.common.base.BaseIdEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "profile")
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
class ProfileJpaEntity extends BaseIdEntity {

    private String username;
    private String bio;
    private String image;
    private long userId;

    /**
     * 회원 가입 할 때 사용 - username, userId 만 필요함
     */
    public ProfileJpaEntity(String username, long userId) {
        this.username = username;
        this.userId = userId;
    }
}
