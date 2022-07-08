package com.deukyun.realworld.user.application.port.out;

import lombok.Value;

@Value
public class InsertUserCommand {

    String email;
    String password;
    long profileId;
}
