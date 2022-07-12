package com.deukyun.realworld.article.application.port.in.dto.command;

import lombok.Value;

@Value
public class AuthorResult {

    String username;
    String bio;
    String image;
    boolean following;
}
