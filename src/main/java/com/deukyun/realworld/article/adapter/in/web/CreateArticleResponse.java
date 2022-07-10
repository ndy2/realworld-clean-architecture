package com.deukyun.realworld.article.adapter.in.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

final class CreateArticleResponse {

    @JsonProperty("article")
    private final Response response;

    public CreateArticleResponse(String slug,
                                 String title,
                                 String description,
                                 String body,
                                 List<String> tagList,
                                 LocalDateTime createdAt,
                                 LocalDateTime updatedAt,
                                 boolean favorited,
                                 long favoritesCount) {

        this.response = new Response(slug,
                title,
                description,
                body,
                tagList,
                createdAt,
                updatedAt,
                favorited,
                favoritesCount);
    }

    @Getter
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
        private AuthorResponse authorResponse;

        public Response(String slug,
                        String title,
                        String description,
                        String body,
                        List<String> tagList,
                        LocalDateTime createdAt,
                        LocalDateTime updatedAt,
                        boolean favorited,
                        long favoritesCount) {
            this.slug = slug;
            this.title = title;
            this.description = description;
            this.body = body;
            this.tagList = tagList;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.favorited = favorited;
            this.favoritesCount = favoritesCount;
        }

        @RequiredArgsConstructor
        public static class AuthorResponse {

            private final String username;
            private final String bio;
            private final String image;
            private final boolean following;
        }
    }
}
