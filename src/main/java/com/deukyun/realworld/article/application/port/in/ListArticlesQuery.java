package com.deukyun.realworld.article.application.port.in;

import com.deukyun.realworld.user.domain.User.UserId;
import lombok.Value;

@Value
public class ListArticlesQuery {

    String tag;
    String author;
    String favorited;
    long limit;
    long offset;

    UserId userId;
}
