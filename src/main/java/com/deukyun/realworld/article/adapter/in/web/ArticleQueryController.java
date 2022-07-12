package com.deukyun.realworld.article.adapter.in.web;

import com.deukyun.realworld.article.adapter.in.dto.query.ArticleResponse;
import com.deukyun.realworld.article.adapter.in.dto.query.ListArticlesResponse;
import com.deukyun.realworld.article.adapter.in.dto.query.SingleArticleResponse;
import com.deukyun.realworld.article.application.port.in.*;
import com.deukyun.realworld.common.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@RestController
public class ArticleQueryController {

    private final ArticleQueries articleQueries;

    /* 아티클 목록 조회 */
    @GetMapping("/api/articles")
    public ListArticlesResponse listArticles(
            @AuthenticationPrincipal SecurityUser securityUser,
            PagingQueryParam pagingQueryParam,
            String tag, String author, String favorited
    ) {
        List<ArticleResult> articleResults = articleQueries.listArticles(
                new ListArticlesQuery(
                        tag,
                        author,
                        favorited,
                        pagingQueryParam.limit,
                        pagingQueryParam.offset,
                        securityUser == null ? null : securityUser.getUserId()
                )
        );

        return new ListArticlesResponse(articleResults.stream().map(ArticleResponse::of).collect(toList()));
    }

    /* 팔로우 중인 유저의 아티클 목록 조회 */
    @GetMapping("/api/articles/feed")
    public ListArticlesResponse feedArticles(
            @AuthenticationPrincipal SecurityUser securityUser,
            PagingQueryParam pagingQueryParam
    ) {
        List<ArticleResult> articleResults = articleQueries.feedArticles(
                new FeedArticlesQuery(
                        pagingQueryParam.limit,
                        pagingQueryParam.offset,
                        securityUser.getUserId()
                )
        );

        return new ListArticlesResponse(articleResults.stream().map(ArticleResponse::of).collect(toList()));
    }

    /* 아티클 단건 조회 */
    @GetMapping("/api/articles/{slug}")
    public SingleArticleResponse getArticle(
            @AuthenticationPrincipal SecurityUser securityUser,
            @PathVariable String slug
    ) {
        ArticleResult articleResult = articleQueries.getArticleBySlug(
                new GetArticleBySlugQuery(
                        slug,
                        securityUser == null ? null : securityUser.getUserId()
                )
        );

        return new SingleArticleResponse(ArticleResponse.of(articleResult));
    }
}
