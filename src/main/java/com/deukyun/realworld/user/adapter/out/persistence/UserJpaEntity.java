package com.deukyun.realworld.user.adapter.out.persistence;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Optional;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class UserJpaEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String password;

    public void update(String updateEmail, String password) {

        Optional.ofNullable(updateEmail).ifPresent(wrapper -> this.email = wrapper);
        Optional.ofNullable(password).ifPresent(wrapper -> this.password = wrapper);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserJpaEntity(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static UserJpaEntity withId(long id) {
        UserJpaEntity userJpaEntity = new UserJpaEntity();
        userJpaEntity.id = id;

        return userJpaEntity;
    }
}
