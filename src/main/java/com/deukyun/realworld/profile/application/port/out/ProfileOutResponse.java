package com.deukyun.realworld.profile.application.port.out;

import lombok.Value;

@Value
public class ProfileOutResponse {

    String username;
    String bio;
    String image;
}
