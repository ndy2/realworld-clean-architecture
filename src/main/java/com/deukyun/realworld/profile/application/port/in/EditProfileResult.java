package com.deukyun.realworld.profile.application.port.in;

import lombok.Value;

@Value
public class EditProfileResult {

    String username;
    String bio;
    String image;
}
