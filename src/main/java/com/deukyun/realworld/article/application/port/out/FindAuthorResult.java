package com.deukyun.realworld.article.application.port.out;

import lombok.Value;

@Value
public class FindAuthorResult {

    String username;
    String bio;
    String image;
}
