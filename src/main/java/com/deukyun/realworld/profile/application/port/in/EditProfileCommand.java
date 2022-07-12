package com.deukyun.realworld.profile.application.port.in;

import com.deukyun.realworld.user.domain.User.UserId;
import lombok.Value;

@Value
public class EditProfileCommand {

    UserId userId;
    String username;
    String bio;
    String image;

    /**
     * no validation due to partial update
     */
    public EditProfileCommand(UserId userId, String username, String bio, String image) {

        this.userId = userId;
        this.username = username;
        this.bio = bio;
        this.image = image;
    }
}
