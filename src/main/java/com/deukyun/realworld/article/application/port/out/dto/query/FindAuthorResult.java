package com.deukyun.realworld.article.application.port.out.dto.query;

import lombok.Value;

@Value
public class FindAuthorResult {

    long id;
    String username;
    String bio;
    String image;
}
