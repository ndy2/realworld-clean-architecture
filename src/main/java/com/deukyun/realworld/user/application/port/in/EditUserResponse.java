package com.deukyun.realworld.user.application.port.in;

import lombok.Value;

@Value
public class EditUserResponse {

    String email;
    String password;
}
