package com.deukyun.realworld.profile.application.port.in.dto.query;

import lombok.Value;

@Value
public class GetProfileByUserIdResult {

    long id;
    String email;
    String username;
    String bio;
    String image;
}
