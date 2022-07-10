package com.deukyun.realworld.article.application.port.out;

import lombok.Value;

@Value
public class FindAuthorResult {

    long id;
    String username;
    String bio;
    String image;
}
