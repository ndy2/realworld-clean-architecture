package com.deukyun.realworld.profile.application.port.out;

import lombok.Value;

@Value
public class InsertProfileCommand {

    long userId;
    String username;
}