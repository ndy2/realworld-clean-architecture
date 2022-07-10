package com.deukyun.realworld.article.adapter.in.web;

import com.deukyun.realworld.article.adapter.in.web.CreateArticleResponse.Response.AuthorResponse;
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
                                 LocalDateTime createdAt) {

        this.response = new Response(slug,
                title,
                description,
                body,
                tagList,
                createdAt);
    }

    public void addAuthorResponse(String username, String bio, String image) {
        response.author = new AuthorResponse(username, bio, image);
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
        private AuthorResponse author;

        @Getter
        @RequiredArgsConstructor
        public static class AuthorResponse {

            private final String username;
            private final String bio;
            private final String image;
        }
    }
}
