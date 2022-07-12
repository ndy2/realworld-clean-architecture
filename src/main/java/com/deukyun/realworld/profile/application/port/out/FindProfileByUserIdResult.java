package com.deukyun.realworld.profile.application.port.out;

import com.deukyun.realworld.profile.domain.Profile.ProfileId;
import com.deukyun.realworld.user.domain.Email;
import lombok.Value;

@Value
public class FindProfileByUserIdResult {

    ProfileId id;
    Email email;
    String username;
    String bio;
    String image;
}
