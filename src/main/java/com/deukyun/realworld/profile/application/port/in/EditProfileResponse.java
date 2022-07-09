package com.deukyun.realworld.profile.application.port.in;

import lombok.Value;

@Value
public class EditProfileResponse {

    String username;
    String bio;
    String image;
}
