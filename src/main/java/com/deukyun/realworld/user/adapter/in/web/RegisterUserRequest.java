package com.deukyun.realworld.user.adapter.in.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class RegisterUserRequest {

    @JsonProperty("user")
    private Request request;

    public String getUsername() {
        return request.username;
    }

    public String getEmail() {
        return request.email;
    }

    public String getPassword() {
        return request.password;
    }

    @Getter
    private static class Request {

        private String username;
        private String email;
        private String password;
    }
}
