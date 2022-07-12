package com.deukyun.realworld.profile.application.port.out;

import com.deukyun.realworld.profile.domain.Profile.ProfileId;
import lombok.Value;

@Value
public class FindProfileByUsernameResult {

    ProfileId id;
    String username;
    String bio;
    String image;

    public FindProfileByUsernameResult(long id, String username, String bio, String image) {
        this.id = new ProfileId(id);
        this.username = username;
        this.bio = bio;
        this.image = image;
    }
}
