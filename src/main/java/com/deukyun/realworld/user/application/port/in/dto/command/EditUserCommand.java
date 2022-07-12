package com.deukyun.realworld.user.application.port.in.dto.command;

import com.deukyun.realworld.user.domain.Email;
import com.deukyun.realworld.user.domain.Password;
import lombok.Value;

@Value
public class EditUserCommand {

    long id;
    Email email;
    Password password;

    public EditUserCommand(long id, String email, String password) {

        this.id = id;
        this.email = email == null ? null : new Email(email);
        this.password = password == null ? null : new Password(password);
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email == null ? null : email.getValue();
    }

    public String getPassword() {
        return password == null ? null : password.getValue();
    }
}
