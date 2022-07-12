package com.deukyun.realworld.article.application.port.in;

import com.deukyun.realworld.user.domain.User.UserId;
import lombok.Value;

@Value
public class GetArticleBySlugQuery {

    String slug;

    UserId userId;
}
