package com.deukyun.realworld.profile.application.port.in;

import lombok.Value;

@Value
public class GetProfileByUserIdResult {

    String username;
    String bio;
    String image;
}