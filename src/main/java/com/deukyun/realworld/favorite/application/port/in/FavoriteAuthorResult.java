package com.deukyun.realworld.favorite.application.port.in;

import lombok.Value;

@Value
public class FavoriteAuthorResult {

    String username;
    String bio;
    String image;
    boolean following;
}
