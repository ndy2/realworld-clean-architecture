package com.deukyun.realworld.user.application.port.out;

import com.deukyun.realworld.user.domain.Email;
import com.deukyun.realworld.user.domain.Password;
import lombok.Value;

@Value
public class InsertUserCommand {

    Email email;
    Password password;
}
