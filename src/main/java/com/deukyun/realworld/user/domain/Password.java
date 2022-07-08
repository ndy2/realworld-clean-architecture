package com.deukyun.realworld.user.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Password {

    private final String value;

    public Password(String value) {

        this.value = value;
    }
}
