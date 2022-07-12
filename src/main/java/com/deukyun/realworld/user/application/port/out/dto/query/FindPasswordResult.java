package com.deukyun.realworld.user.application.port.out.dto.query;


import lombok.Value;

@Value
public class FindPasswordResult {

    long id;
    String password;
}
