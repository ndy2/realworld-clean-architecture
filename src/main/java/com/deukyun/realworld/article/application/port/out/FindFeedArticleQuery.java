package com.deukyun.realworld.article.application.port.out;

import com.deukyun.realworld.user.domain.User.UserId;
import lombok.Value;

@Value
public class FindFeedArticleQuery {

    long limit;
    long offset;
    UserId userId;
}
