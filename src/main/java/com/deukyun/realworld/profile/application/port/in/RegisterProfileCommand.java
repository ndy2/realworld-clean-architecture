package com.deukyun.realworld.profile.application.port.in;

import com.deukyun.realworld.user.domain.User.UserId;
import lombok.Value;

import static com.google.common.base.Preconditions.checkArgument;
import static org.springframework.util.StringUtils.hasText;

@Value
public class RegisterProfileCommand {

    UserId userId;
    String username;

    public RegisterProfileCommand(UserId userId, String username) {
        checkArgument(hasText(username), "username is not provided");

        this.userId = userId;
        this.username = username;
    }
}
