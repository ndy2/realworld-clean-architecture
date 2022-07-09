package com.deukyun.realworld.user.application.port.out;

import lombok.Value;

@Value
public class UpdateUserCommand {

    long id;
    String email;
    String password;
}
