package com.deukyun.realworld.profile.adapter.in.dto.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public final class GetProfileResponse {

    @JsonProperty("profile")
    private final Response response;

    public GetProfileResponse(String username, String bio, String image, boolean following) {
        this.response = new Response(username, bio, image, following);
    }

    @Getter
    public static class Response {

        private final String username;
        private final String bio;
        private final String image;
        private final boolean following;

        public Response(String username, String bio, String image, boolean following) {
            this.username = username;
            this.bio = bio;
            this.image = image;
            this.following = following;
        }
    }
}
