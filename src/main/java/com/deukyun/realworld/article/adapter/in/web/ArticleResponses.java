package com.deukyun.realworld.article.adapter.in.web;

import com.deukyun.realworld.article.application.port.in.ArticleResult;
import com.deukyun.realworld.article.application.port.in.AuthorResult;
import com.deukyun.realworld.favorite.application.port.in.FavoriteArticleResult;
import com.deukyun.realworld.favorite.application.port.in.FavoriteAuthorResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

final class ArticleResponses {

    @Getter
    public static class ListArticlesResponse {

        private final List<Response> articles;
        private final int articleCount;

        public ListArticlesResponse(List<Response> articles) {
            this.articles = articles;
            this.articleCount = articles.size();
        }
    }

    public static class SingleArticleResponse {

        public final Response article;

        public SingleArticleResponse(Response article) {
            this.article = article;
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class Response {
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

        static Response of(ArticleResult articleResult) {
            AuthorResult authorResult = articleResult.getAuthorResult();

            return new Response(
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

        static Response of(FavoriteArticleResult articleResult) {
            FavoriteAuthorResult authorResult = articleResult.getAuthor();

            return new Response(
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
}
