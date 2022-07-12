package com.deukyun.realworld.profile.application.port.in;

import com.deukyun.realworld.profile.application.port.in.dto.query.GetProfileByUsernameResult;

public interface GetProfileByUsernameQuery {

    /**
     * @param userId   is Optional - 미인증 사용자의 경우 null
     * @param username - 조회하고자 하는 사용자의 이름
     * @return 조회 정보
     */
    GetProfileByUsernameResult getProfileByUsername(Long userId, String username);
}
