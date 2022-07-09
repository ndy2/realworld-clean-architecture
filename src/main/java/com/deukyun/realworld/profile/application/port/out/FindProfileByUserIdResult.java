package com.deukyun.realworld.profile.application.port.out;

import lombok.Value;

@Value
public class FindProfileByUserIdResult {

    String email;
    String username;
    String bio;
    String image;
}
