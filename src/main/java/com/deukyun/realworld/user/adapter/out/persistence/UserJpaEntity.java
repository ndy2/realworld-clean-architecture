package com.deukyun.realworld.user.adapter.out.persistence;


import com.deukyun.realworld.common.base.BaseIdEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Optional;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
class UserJpaEntity extends BaseIdEntity {

    private String email;
    private String password;

    public void update(String updateEmail, String password) {
        Optional.ofNullable(updateEmail).ifPresent(wrapper -> this.email = wrapper);
        Optional.ofNullable(password).ifPresent(wrapper -> this.password = wrapper);

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
