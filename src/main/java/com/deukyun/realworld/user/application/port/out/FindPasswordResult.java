package com.deukyun.realworld.user.application.port.out;


import lombok.Value;

@Value
public class FindPasswordResult {

    long id;
    String password;
}
