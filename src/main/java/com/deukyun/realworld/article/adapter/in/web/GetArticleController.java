package com.deukyun.realworld.article.adapter.in.web;

import com.deukyun.realworld.article.application.port.in.FeedArticlesQuery;
import com.deukyun.realworld.article.application.port.in.GetArticleBySlugQuery;
import com.deukyun.realworld.article.application.port.in.ListArticlesQuery;
import com.deukyun.realworld.configuration.jwt.JwtAuthenticationToken.JwtAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GetArticleController {

    private final ListArticlesQuery listArticlesQuery;
    private final FeedArticlesQuery feedArticlesQuery;
    private final GetArticleBySlugQuery getArticleBySlugQuery;

    @GetMapping("/api/articles")
    public void listArticles(
            PagingQueryParam pagingQueryParam,
            String tag, String author, String favorited
    ) {

    }

    @GetMapping("/api/articles/feed")
    public void feedArticles(
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            PagingQueryParam pagingQueryParam
    ) {

    }

    @GetMapping("/api/articles/{slug}")
    public void getArticle(
            @PathVariable String slug
    ) {

    }
}
