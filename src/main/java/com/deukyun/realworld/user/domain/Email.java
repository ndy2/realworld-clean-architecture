package com.deukyun.realworld.user.domain;

import lombok.EqualsAndHashCode;

import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkArgument;

@EqualsAndHashCode
public class Email {

    private final String value;

    public Email(String value) {
        checkArgument(!Pattern.matches("^(.+)@(.+)$", value), "Invalid email provided");

        this.value = value;
    }
}
