package com.deukyun.realworld.article.adapter.in.web;

import com.deukyun.realworld.article.domain.Tags;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

final class CreateArticleRequest {

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

    public Tags getTags() {
        return new Tags(request.tagList);
    }

    @Getter
    private static class Request {

        private String title;
        private String description;
        private String body;
        private List<String> tagList;
    }
}
