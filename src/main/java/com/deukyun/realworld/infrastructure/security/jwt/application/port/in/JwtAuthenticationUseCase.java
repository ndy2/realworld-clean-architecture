package com.deukyun.realworld.infrastructure.security.jwt.application.port.in;

public interface JwtAuthenticationUseCase {

    /**
     * 인증 처리 - 실패시 예외 던짐
     *
     * @throws com.deukyun.realworld.common.exception.RealworldRuntimeException
     */
    void authenticate(Object principal, Object credentials);
}
