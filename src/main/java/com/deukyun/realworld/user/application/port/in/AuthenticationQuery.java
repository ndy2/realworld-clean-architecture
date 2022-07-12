package com.deukyun.realworld.user.application.port.in;

public interface AuthenticationQuery {

    /**
     * 인증 처리 - 실패시 예외 던짐
     *
     * @throws java.lang.IllegalArgumentException
     */
    long authenticate(Object principal, Object credentials);
}
