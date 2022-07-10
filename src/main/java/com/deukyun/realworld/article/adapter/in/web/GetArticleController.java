package com.deukyun.realworld.article.adapter.in.web;

import com.deukyun.realworld.article.adapter.in.web.ArticleResponses.ListArticlesResponse;
import com.deukyun.realworld.article.adapter.in.web.ArticleResponses.Response;
import com.deukyun.realworld.article.adapter.in.web.ArticleResponses.SingleArticleResponse;
import com.deukyun.realworld.article.application.port.in.ArticleQueries;
import com.deukyun.realworld.article.application.port.in.ArticleResult;
import com.deukyun.realworld.article.application.port.in.FeedArticlesCommand;
import com.deukyun.realworld.article.application.port.in.ListArticlesCommand;
import com.deukyun.realworld.configuration.jwt.JwtAuthenticationToken.JwtAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@RestController
public class GetArticleController {

    private final ArticleQueries articleQueries;

    @GetMapping("/api/articles")
    public ListArticlesResponse listArticles(
            PagingQueryParam pagingQueryParam,
            String tag, String author, String favorited
    ) {
        List<ArticleResult> articleResults = articleQueries.listArticles(
                new ListArticlesCommand(
                        tag,
                        author,
                        favorited,
                        pagingQueryParam.limit,
                        pagingQueryParam.offset
                )
        );

        return new ListArticlesResponse(
                articleResults.stream().map(Response::of).collect(toList())
        );
    }

    @GetMapping("/api/articles/feed")
    public ListArticlesResponse feedArticles(
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            PagingQueryParam pagingQueryParam
    ) {
        List<ArticleResult> articleResults = articleQueries.feedArticles(
                new FeedArticlesCommand(
                        pagingQueryParam.limit,
                        pagingQueryParam.offset,
                        jwtAuthentication.getUserId()
                )
        );

        return new ListArticlesResponse(
                articleResults.stream().map(Response::of).collect(toList())
        );
    }

    @GetMapping("/api/articles/{slug}")
    public SingleArticleResponse getArticle(
            @PathVariable String slug
    ) {
        ArticleResult articleResult = articleQueries.getArticleBySlug(slug);

        return new SingleArticleResponse(Response.of(articleResult));
    }
}
