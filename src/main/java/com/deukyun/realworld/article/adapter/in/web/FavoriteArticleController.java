package com.deukyun.realworld.article.adapter.in.web;

import com.deukyun.realworld.article.adapter.in.web.ArticleResponses.Response;
import com.deukyun.realworld.article.adapter.in.web.ArticleResponses.SingleArticleResponse;
import com.deukyun.realworld.favorite.application.port.in.FavoriteArticleResult;
import com.deukyun.realworld.favorite.application.port.in.FavoriteArticleUseCase;
import com.deukyun.realworld.favorite.application.port.in.UnfavoriteArticleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.deukyun.realworld.configuration.jwt.JwtAuthenticationToken.JwtAuthentication;

@RequiredArgsConstructor
@RestController
public class FavoriteArticleController {

    private final FavoriteArticleUseCase favoriteArticleUseCase;
    private final UnfavoriteArticleUseCase unfavoriteArticleUseCase;

    @PostMapping("/api/articles/{slug}/favorite")
    public SingleArticleResponse favoriteArticle(
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @PathVariable String slug
    ) {
        long userId = jwtAuthentication.getUserId();

        FavoriteArticleResult articleResult = favoriteArticleUseCase.favorite(userId, slug);

        return new SingleArticleResponse(Response.of(articleResult));
    }

    @DeleteMapping("/api/articles/{slug}/unfavorite")
    public SingleArticleResponse unfavoriteArticle(
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @PathVariable String slug
    ) {
        long userId = jwtAuthentication.getUserId();

        FavoriteArticleResult articleResult = unfavoriteArticleUseCase.unfavorite(userId, slug);

        return new SingleArticleResponse(Response.of(articleResult));
    }

}
