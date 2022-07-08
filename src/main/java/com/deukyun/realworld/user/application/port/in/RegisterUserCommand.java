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

    public RegisterUserCommand(String email, String password, String username) {
        checkArgument(hasText(username), "username is not provided");

        this.email = new Email(email);
        this.password = new Password(password);
        this.username = username;
    }
}
