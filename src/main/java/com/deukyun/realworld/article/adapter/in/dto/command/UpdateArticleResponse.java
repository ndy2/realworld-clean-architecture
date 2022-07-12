package com.deukyun.realworld.article.adapter.in.dto.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class UpdateArticleResponse {

    @JsonProperty("article")
    private final Response response;

    public UpdateArticleResponse(String slug,
                                 String title,
                                 String description,
                                 String body,
                                 List<String> tagList,
                                 LocalDateTime createdAt,
                                 LocalDateTime updatedAt,
                                 boolean favorited,
                                 long favoritesCount) {

        this.response = new Response(
                slug,
                title,
                description,
                body,
                tagList,
                createdAt,
                updatedAt,
                favorited,
                favoritesCount
        );
    }

    public void addAuthorResponse(String username, String bio, String image, boolean following) {
        response.author = new Response.AuthorResponse(username, bio, image, following);
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
        private AuthorResponse author;

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
