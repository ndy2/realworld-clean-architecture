package com.deukyun.realworld.profile.application.port.in.dto.query;

import lombok.Value;

@Value
public class GetProfileByUsernameResult {

    String username;
    String bio;
    String image;
    boolean isFollow;
}
