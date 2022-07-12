package com.deukyun.realworld.user.adapter.in.web;

import com.deukyun.realworld.user.domain.Email;
import com.deukyun.realworld.user.domain.Password;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

final class AuthenticationRequest {

    @JsonProperty("user")
    private Request request;

    public Email getEmail() {
        return new Email(request.email);
    }

    public Password getPassword() {
        return new Password(request.password);
    }

    @Getter
    private static class Request {

        private String email;
        private String password;
    }
}
