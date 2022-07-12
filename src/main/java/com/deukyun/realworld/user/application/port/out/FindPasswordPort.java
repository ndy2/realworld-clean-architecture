package com.deukyun.realworld.user.application.port.out;

import com.deukyun.realworld.user.application.port.out.dto.query.FindPasswordResult;

import java.util.Optional;

public interface FindPasswordPort {

    Optional<FindPasswordResult> findPasswordByEmail(String email);
}
