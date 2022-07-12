package com.deukyun.realworld.article.adapter.in.dto.query;

import com.deukyun.realworld.article.application.port.in.ArticleResult;
import com.deukyun.realworld.article.application.port.in.AuthorResult;
import com.deukyun.realworld.favorite.application.port.in.FavoriteArticleResult;
import com.deukyun.realworld.favorite.application.port.in.FavoriteAuthorResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class ArticleResponse {
    private final String slug;
    private final String title;
    private final String description;
    private final String body;
    private final List<String> tagList;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final boolean favorited;
    private final long favoritesCount;
    private final AuthorResponse author;

    @Getter
    @RequiredArgsConstructor
    public static class AuthorResponse {

        private final String username;
        private final String bio;
        private final String image;
        private final boolean following;
    }

    public static ArticleResponse of(ArticleResult articleResult) {
        AuthorResult authorResult = articleResult.getAuthorResult();

        return new ArticleResponse(
                articleResult.getSlug(),
                articleResult.getTitle(),
                articleResult.getDescription(),
                articleResult.getBody(),
                articleResult.getTagList(),
                articleResult.getCreatedAt(),
                articleResult.getUpdatedAt(),
                articleResult.isFavorited(),
                articleResult.getFavoritesCount(),
                new AuthorResponse(
                        authorResult.getUsername(),
                        authorResult.getBio(),
                        authorResult.getImage(),
                        authorResult.isFollowing()
                )
        );
    }

    public static ArticleResponse of(FavoriteArticleResult articleResult) {
        FavoriteAuthorResult authorResult = articleResult.getAuthor();

        return new ArticleResponse(
                articleResult.getSlug(),
                articleResult.getTitle(),
                articleResult.getDescription(),
                articleResult.getBody(),
                articleResult.getTagList(),
                articleResult.getCreatedAt(),
                articleResult.getUpdatedAt(),
                articleResult.isFavorited(),
                articleResult.getFavoritesCount(),
                new AuthorResponse(
                        authorResult.getUsername(),
                        authorResult.getBio(),
                        authorResult.getImage(),
                        authorResult.isFollowing()
                )
        );
    }
}

