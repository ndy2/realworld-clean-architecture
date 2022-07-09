package com.deukyun.realworld.profile.application.port.in;

/**
 * 사용자의 이메일 까지 조회함
 */
public interface GetProfileByUserIdQuery {

    GetProfileByUserIdResult getProfileByUserId(Long id);
}
