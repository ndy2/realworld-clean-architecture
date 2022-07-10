package com.deukyun.realworld.article.application.service;

import com.deukyun.realworld.article.application.port.in.FeedArticlesQuery;
import com.deukyun.realworld.article.application.port.in.GetArticleBySlugQuery;
import com.deukyun.realworld.article.application.port.in.ListArticlesQuery;
import com.deukyun.realworld.common.component.Query;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Query
class GetArticleService implements
        ListArticlesQuery,
        FeedArticlesQuery,
        GetArticleBySlugQuery {


}
