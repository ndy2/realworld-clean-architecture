package com.deukyun.realworld.profile.application.port.out;

import com.deukyun.realworld.profile.application.port.out.dto.query.FindProfileByUsernameResult;

public interface FindProfileByUsernamePort {

    FindProfileByUsernameResult findProfileByUsername(String username);
}
