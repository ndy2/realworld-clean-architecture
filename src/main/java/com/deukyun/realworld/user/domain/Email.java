package com.deukyun.realworld.user.domain;

import lombok.Value;

import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkArgument;

@Value
public class Email {

    String value;

    public Email(String value) {
        checkArgument(Pattern.matches("^(.+)@(.+)$", value), "Invalid email provided");

        this.value = value;
    }
}
