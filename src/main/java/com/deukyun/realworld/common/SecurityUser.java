package com.deukyun.realworld.common;

import lombok.Value;

/**
 * 전역적으로 활용하는 Principal 객체
 */
@Value
public class SecurityUser {

    long userId;
    String token;
}
