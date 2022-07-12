package com.deukyun.realworld.common;

import com.deukyun.realworld.user.domain.User.UserId;
import lombok.Value;

@Value
public class SecurityUser {

    UserId userId;
    String token;
}
