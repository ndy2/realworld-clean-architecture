package com.deukyun.realworld.user.adapter.in.dto.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public final class EditUserRequest {

    @JsonProperty("user")
    private Request request;

    public String getEmail() {
        return request.email;
    }

    public String getPassword() {
        return request.password;
    }

    public String getUsername() {
        return request.username;
    }

    public String getImage() {
        return request.image;
    }

    public String getBio() {
        return request.bio;
    }

    @Getter
    private static class Request {

        private String email;
        private String password;
        private String username;
        private String image;

        private String bio;
    }

}
