package com.deukyun.realworld.user.application.port.in;

public interface AuthenticationQuery {

    /**
     * 인증 처리 - 실패시 예외 던짐
     *
     * @throws com.deukyun.realworld.common.exception.RealworldRuntimeException
     */
    long authenticate(Object principal, Object credentials);
}
