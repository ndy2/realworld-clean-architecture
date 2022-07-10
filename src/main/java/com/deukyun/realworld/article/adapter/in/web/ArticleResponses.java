package com.deukyun.realworld.article.adapter.in.web;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

final class ArticleResponses {

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
        private final AuthorResponse authorResponse;

        @Getter
        @RequiredArgsConstructor
        public static class AuthorResponse {

            private final String username;
            private final String bio;
            private final String image;
            private final boolean following;
        }
    }
}
