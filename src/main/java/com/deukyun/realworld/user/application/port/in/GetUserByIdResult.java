package com.deukyun.realworld.user.application.port.in;

import com.deukyun.realworld.user.domain.Email;
import lombok.Value;

@Value
public class GetUserByIdResult {

    Email email;
}
