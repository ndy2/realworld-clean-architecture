package com.deukyun.realworld.profile.application.port.in;

import lombok.Value;

@Value
public class FollowUserResult {

    String username;
    String bio;
    String image;
    boolean following;
}
