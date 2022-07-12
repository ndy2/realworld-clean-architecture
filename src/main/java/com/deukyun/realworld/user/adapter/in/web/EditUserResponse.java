package com.deukyun.realworld.user.adapter.in.web;

import com.deukyun.realworld.user.domain.Email;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

final class EditUserResponse {

    @JsonProperty("user")
    private final Response response;

    /**
     * @param email    - nullable
     * @param username - nullable
     * @param bio      - nullable
     * @param image    - nullable
     */
    public EditUserResponse(Email email, String username, String bio, String image) {
        this.response = new Response(
                email == null ? null : email.getValue(),
                username,
                bio,
                image
        );
    }

    @Getter
    public static class Response {

        private final String email;
        private final String username;
        private final String bio;
        private final String image;

        public Response(String email, String username, String bio, String image) {
            this.email = email;
            this.username = username;
            this.bio = bio;
            this.image = image;
        }
    }
}
