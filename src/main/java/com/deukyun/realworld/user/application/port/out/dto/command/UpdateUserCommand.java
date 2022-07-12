package com.deukyun.realworld.user.application.port.out.dto.command;

import lombok.Value;

@Value
public class UpdateUserCommand {

    long id;
    String email;
    String password;
}
