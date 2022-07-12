package com.deukyun.realworld.profile.application.port.out.dto.command;

import lombok.Value;

@Value
public class InsertProfileCommand {

    long userId;
    String username;
}
