package com.deukyun.realworld.user.adapter.in.dto.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public final class AuthenticationRequest {

    @JsonProperty("user")
    private Request request;

    public String getEmail() {
        return request.email;
    }

    public String getPassword() {
        return request.password;
    }

    @Getter
    private static class Request {

        private String email;
        private String password;
    }
}
