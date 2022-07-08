package com.deukyun.realworld.user.domain;

import lombok.Value;

@Value
public class Password {

    String value;

    public Password(String value) {

        this.value = value;
    }
}
