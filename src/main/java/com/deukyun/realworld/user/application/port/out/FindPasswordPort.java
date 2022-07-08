package com.deukyun.realworld.user.application.port.out;

import java.util.Optional;

public interface FindPasswordPort {

    Optional<String> findPasswordByEmail(String email);
}
