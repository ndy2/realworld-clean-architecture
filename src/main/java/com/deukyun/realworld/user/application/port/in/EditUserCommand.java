package com.deukyun.realworld.user.application.port.in;

import lombok.Value;

@Value
public class EditUserCommand {

    String email;
    String password;
}
