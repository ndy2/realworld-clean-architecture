package com.deukyun.realworld.favorite.application.port.in.dto.command;

import lombok.Value;

@Value
public class FavoriteAuthorResult {

    String username;
    String bio;
    String image;
    boolean following;
}
