package com.deukyun.realworld.profile.application.port.out;

import lombok.Value;

@Value
public class ProfileResponse {

    String username;
    String bio;
    String image;
}
