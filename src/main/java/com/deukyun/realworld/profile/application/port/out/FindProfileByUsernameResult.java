package com.deukyun.realworld.profile.application.port.out;

import lombok.Value;

@Value
public class FindProfileByUsernameResult {

    String username;
    String bio;
    String image;
}
