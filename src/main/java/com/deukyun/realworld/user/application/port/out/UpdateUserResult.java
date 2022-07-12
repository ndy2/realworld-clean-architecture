package com.deukyun.realworld.user.application.port.out;

import com.deukyun.realworld.user.domain.Email;
import lombok.Value;

@Value
public class UpdateUserResult {

    Email email;
}
