package com.deukyun.realworld.article.application.port.out;

import lombok.Value;

@Value
public class FindFeedArticleQuery {

    long limit;
    long offset;
    long userId;
}
