package com.deukyun.realworld.profile.application.port.in;

import com.deukyun.realworld.profile.application.port.in.dto.query.GetProfileByUserIdResult;

/**
 * 사용자의 이메일 까지 조회함
 */
public interface GetProfileByUserIdQuery {

    GetProfileByUserIdResult getProfileByUserId(Long id);
}
