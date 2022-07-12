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

    public EditUserCommand(UserId userId, Email email, Password password) {

        this.userId = userId;
        this.email = email;
        this.password = password;
    }
}
