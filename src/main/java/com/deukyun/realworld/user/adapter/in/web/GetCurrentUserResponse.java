package com.deukyun.realworld.user.adapter.in.web;

import com.deukyun.realworld.user.domain.Email;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

final class GetCurrentUserResponse {

    @JsonProperty("user")
    private final Response response;

    public GetCurrentUserResponse(Email email, String token, String username, String bio, String image) {
        this.response = new Response(
                email.getValue(),
                token,
                username,
                bio,
                image);
    }

    @Getter
    public static class Response {

        private final String email;
        private final String token;
        private final String username;
        private final String bio;
        private final String image;

        public Response(String email, String token, String username, String bio, String image) {
            this.email = email;
            this.token = token;
            this.username = username;
            this.bio = bio;
            this.image = image;
        }
    }
}
