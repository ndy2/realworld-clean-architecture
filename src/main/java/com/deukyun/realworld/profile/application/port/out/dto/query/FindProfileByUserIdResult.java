package com.deukyun.realworld.profile.application.port.out.dto.query;

import lombok.Value;

@Value
public class FindProfileByUserIdResult {

    long id;
    String email;
    String username;
    String bio;
    String image;
}
