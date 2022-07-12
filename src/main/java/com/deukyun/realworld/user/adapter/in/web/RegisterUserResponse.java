package com.deukyun.realworld.user.adapter.in.web;

import com.deukyun.realworld.user.domain.Email;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

final class RegisterUserResponse {

    @JsonProperty("user")
    private final Response response;

    public RegisterUserResponse(Email email, String username) {
        this.response = new Response(email.getValue(), username);
    }

    @Getter
    public static class Response {

        private final String email;
        private final String username;

        public Response(String email, String username) {
            this.email = email;
            this.username = username;
        }
    }
}
