package com.deukyun.realworld.profile.application.port.out;

import com.deukyun.realworld.user.domain.User.UserId;
import lombok.Value;

@Value
public class InsertProfileCommand {

    UserId userId;
    String username;
}
