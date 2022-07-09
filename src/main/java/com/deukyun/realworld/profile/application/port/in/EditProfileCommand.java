package com.deukyun.realworld.profile.application.port.in;

import lombok.Value;

import static com.google.common.base.Preconditions.checkArgument;
import static org.springframework.util.StringUtils.hasText;

@Value
public class EditProfileCommand {

    String username;
    String bio;
    String image;

    public EditProfileCommand(String username, String bio, String image) {
        checkArgument(hasText(username), "username is not provided");
        checkArgument(hasText(bio), "bio is not provided");
        checkArgument(hasText(image), "image is not provided");


        this.username = username;
        this.bio = bio;
        this.image = image;
    }
}
