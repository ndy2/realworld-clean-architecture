package com.deukyun.realworld.user.application.port.out;


import lombok.Value;

@Value
public class FindPasswordResponse {

    long id;
    String password;
}
