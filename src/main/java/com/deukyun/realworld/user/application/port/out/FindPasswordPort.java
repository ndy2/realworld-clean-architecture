package com.deukyun.realworld.user.application.port.out;

import com.deukyun.realworld.user.domain.Email;

import java.util.Optional;

public interface FindPasswordPort {

    Optional<FindPasswordResult> findPasswordByEmail(Email email);
}
