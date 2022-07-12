package com.deukyun.realworld.article.application.port.out.dto.command;

import lombok.Value;

@Value
public class CommandAuthorResult {

    long id;
    String username;
    String bio;
    String image;
}
