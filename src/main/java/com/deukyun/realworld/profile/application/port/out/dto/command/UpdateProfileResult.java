package com.deukyun.realworld.profile.application.port.out.dto.command;

import lombok.Value;

@Value
public class UpdateProfileResult {

    String username;
    String bio;
    String image;
}
