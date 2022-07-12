package com.deukyun.realworld.profile.application.port.in;

import com.deukyun.realworld.user.domain.User.UserId;

public interface GetProfileByUsernameQuery {

    /**
     * @param userId   - 사용자 아이디
     * @param username - 조회하고자 하는 사용자의 이름
     * @return 조회 정보
     */
    GetProfileByUsernameResult getProfileByUsername(UserId userId, String username);
}
