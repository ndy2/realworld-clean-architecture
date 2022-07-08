package com.deukyun.realworld.common;

import com.deukyun.realworld.user.domain.Email;
import com.deukyun.realworld.user.domain.Password;
import com.deukyun.realworld.user.domain.User;

public class Fixture {

    public static User createUser() {
        return new User(
                new Email("jake@jake.jake"),
                new Password("jakejake")
        );
    }
}
