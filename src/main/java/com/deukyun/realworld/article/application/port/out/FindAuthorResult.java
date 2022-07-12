package com.deukyun.realworld.article.application.port.out;

import com.deukyun.realworld.profile.domain.Profile.ProfileId;
import lombok.Value;

@Value
public class FindAuthorResult {

    ProfileId id;
    String username;
    String bio;
    String image;
}
