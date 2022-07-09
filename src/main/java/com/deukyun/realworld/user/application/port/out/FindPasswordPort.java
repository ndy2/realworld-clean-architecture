package com.deukyun.realworld.user.application.port.out;

import java.util.Optional;

public interface FindPasswordPort {

    Optional<FindPasswordResult> findPasswordByEmail(String email);
}
