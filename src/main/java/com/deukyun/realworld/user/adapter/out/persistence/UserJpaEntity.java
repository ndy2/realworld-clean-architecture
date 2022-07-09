package com.deukyun.realworld.user.adapter.out.persistence;


import com.deukyun.realworld.common.base.BaseIdEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
class UserJpaEntity extends BaseIdEntity {

    private String email;
    private String password;

    public void update(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
