package com.deukyun.realworld.user.application.port.in;

import com.deukyun.realworld.user.domain.User.UserId;

public interface AuthenticationQuery {

    /**
     * 인증 처리 - 실패시 예외 던짐
     * <p>
     * 성공시 사용자 아이디 반환
     *
     * @throws com.deukyun.realworld.common.exception.RealworldRuntimeException
     */
    UserId authenticate(Object principal, Object credentials);
}
