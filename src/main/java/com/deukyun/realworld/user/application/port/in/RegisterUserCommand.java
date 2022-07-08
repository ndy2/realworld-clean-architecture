package com.deukyun.realworld.user.application.port.in;

import com.deukyun.realworld.user.domain.Email;
import com.deukyun.realworld.user.domain.Password;
import lombok.Value;

import static com.google.common.base.Preconditions.checkArgument;
import static org.springframework.util.StringUtils.hasText;

@Value
public class RegisterUserCommand {

    Email email;

    Password password;

    String username;

    public RegisterUserCommand(Email email, Password password, String username) {
        checkArgument(hasText(username), "username is not provided");

        this.email = email;
        this.password = password;
        this.username = username;
    }
}
