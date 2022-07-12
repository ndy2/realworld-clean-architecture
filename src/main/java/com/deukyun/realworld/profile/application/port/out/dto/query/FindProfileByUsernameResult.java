package com.deukyun.realworld.profile.application.port.out.dto.query;

import lombok.Value;

@Value
public class FindProfileByUsernameResult {

    long id;
    String username;
    String bio;
    String image;
}
