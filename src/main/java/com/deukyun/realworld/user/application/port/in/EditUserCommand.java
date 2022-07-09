package com.deukyun.realworld.user.application.port.in;

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
        this.email = new Email(email);
        this.password = new Password(password);
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email.getValue();
    }

    public String getPassword() {
        return password.getValue();
    }
}
