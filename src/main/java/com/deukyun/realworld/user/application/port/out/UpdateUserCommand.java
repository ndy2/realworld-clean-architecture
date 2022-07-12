package com.deukyun.realworld.user.application.port.out;

import com.deukyun.realworld.user.domain.Email;
import com.deukyun.realworld.user.domain.Password;
import com.deukyun.realworld.user.domain.User.UserId;
import lombok.Value;

@Value
public class UpdateUserCommand {

    UserId userId;
    Email email;
    Password password;
}
