package com.deukyun.realworld.user.application.port.out.dto.command;

import lombok.Value;

@Value
public class InsertUserCommand {

    String email;
    String password;
}
