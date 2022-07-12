package com.deukyun.realworld.follow.application.port.in.dto.query;

import lombok.Value;

@Value
public class FollowUserResult {

    String username;
    String bio;
    String image;
    boolean following;
}
