package com.deukyun.realworld.article.adapter.in.web;

import com.deukyun.realworld.article.adapter.in.dto.query.ArticleResponse;
import com.deukyun.realworld.article.adapter.in.dto.query.SingleArticleResponse;
import com.deukyun.realworld.common.SecurityUser;
import com.deukyun.realworld.favorite.application.port.in.FavoriteArticleUseCases;
import com.deukyun.realworld.favorite.application.port.in.dto.command.FavoriteArticleResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class FavoriteArticleController {

    private final FavoriteArticleUseCases favoriteArticleUseCases;

    @PostMapping("/api/articles/{slug}/favorite")
    public SingleArticleResponse favoriteArticle(
            @AuthenticationPrincipal SecurityUser securityUser,
            @PathVariable String slug
    ) {
        long userId = securityUser.getUserId();

        FavoriteArticleResult articleResult = favoriteArticleUseCases.favorite(userId, slug);

        return new SingleArticleResponse(ArticleResponse.of(articleResult));
    }

    @DeleteMapping("/api/articles/{slug}/unfavorite")
    public SingleArticleResponse unfavoriteArticle(
            @AuthenticationPrincipal SecurityUser securityUser,
            @PathVariable String slug
    ) {
        long userId = securityUser.getUserId();

        FavoriteArticleResult articleResult = favoriteArticleUseCases.unfavorite(userId, slug);

        return new SingleArticleResponse(ArticleResponse.of(articleResult));
    }

}
