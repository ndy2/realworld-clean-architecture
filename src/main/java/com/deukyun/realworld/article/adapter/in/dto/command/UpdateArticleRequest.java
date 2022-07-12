package com.deukyun.realworld.article.adapter.in.dto.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public final class UpdateArticleRequest {

    @JsonProperty("article")
    private Request request;

    public String getTitle() {
        return request.title;
    }

    public String getDescription() {
        return request.description;
    }

    public String getBody() {
        return request.body;
    }

    @Getter
    private static class Request {

        private String title;
        private String description;
        private String body;
    }
}
