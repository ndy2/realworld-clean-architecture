package com.deukyun.realworld.profile.application.port.in.dto.command;

import lombok.Value;

@Value
public class EditProfileResult {

    String username;
    String bio;
    String image;
}
