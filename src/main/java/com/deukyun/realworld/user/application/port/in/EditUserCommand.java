package com.deukyun.realworld.user.application.port.in;

import com.deukyun.realworld.user.domain.Email;
import com.deukyun.realworld.user.domain.Password;
import lombok.Value;

@Value
public class EditUserCommand {

    Email email;
    Password password;

    public EditUserCommand(String email, String password) {

        this.email = new Email(email);
        this.password = new Password(password);
    }
}
