package com.deukyun.realworld.user.application.port.in;

import com.deukyun.realworld.user.domain.Password;

public interface CustomPasswordEncoder {

    Password encode(Password rawPassword);

    boolean matches(Password rawPassword, Password encodedPassword);

}