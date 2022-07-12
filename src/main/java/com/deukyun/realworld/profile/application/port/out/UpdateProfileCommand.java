package com.deukyun.realworld.profile.application.port.out;

import lombok.Value;

import static com.deukyun.realworld.user.domain.User.UserId;

@Value
public class UpdateProfileCommand {

    UserId userId;
    String username;
    String bio;
    String image;
}
