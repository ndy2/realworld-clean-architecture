package com.deukyun.realworld.user.adapter.in.web;

import com.deukyun.realworld.user.domain.Email;
import com.deukyun.realworld.user.domain.Password;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

final class EditUserRequest {

    @JsonProperty("user")
    private Request request;

    public Email getEmail() {
        return new Email(request.email);
    }

    public Password getPassword() {
        return new Password(request.password);
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
