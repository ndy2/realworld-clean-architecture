package com.deukyun.realworld.profile.application.port.out;

import lombok.Value;

@Value
public class InsertProfileOutCommand {

    long userId;
    String username;
}
