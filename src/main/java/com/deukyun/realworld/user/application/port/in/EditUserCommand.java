package com.deukyun.realworld.user.application.port.in;

import com.deukyun.realworld.user.domain.Email;
import com.deukyun.realworld.user.domain.Password;
import com.deukyun.realworld.user.domain.User.UserId;
import lombok.Value;

@Value
public class EditUserCommand {

    UserId userId;
    Email email;
    Password password;

    public EditUserCommand(UserId userId, String email, String password) {

        this.userId = userId;
        this.email = email == null ? null : new Email(email);
        this.password = password == null ? null : new Password(password);
    }
}
