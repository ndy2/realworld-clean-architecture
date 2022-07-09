package com.deukyun.realworld.follow.application.port.out;

import java.util.Optional;

public interface CheckFollowPort {

    /**
     * 프로필 아이디에 대해 팔로우가 이미 존재하는지 확인
     * <p>
     * 존재한다면 id 리턴, 존재하지 않으면 null 리턴
     */
    Optional<Long> checkFollow(long followerId, long followeeId);
}
