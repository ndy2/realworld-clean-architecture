package com.deukyun.realworld.profile.application.port.in;

import lombok.Value;

import static com.google.common.base.Preconditions.checkArgument;
import static org.springframework.util.StringUtils.hasText;

@Value
public class RegisterProfileCommand {

    long userId;
    String username;

    public RegisterProfileCommand(long userId, String username) {
        checkArgument(hasText(username), "username is not provided");

        this.userId = userId;
        this.username = username;
    }
}
