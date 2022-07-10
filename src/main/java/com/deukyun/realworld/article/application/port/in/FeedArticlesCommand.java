package com.deukyun.realworld.article.application.port.in;

import lombok.Value;

@Value
public class FeedArticlesCommand {

    long limit;
    long offset;
    long userId;
}
