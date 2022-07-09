package com.deukyun.realworld.follow.application.port.out;

public interface CheckFollowPort {

    /**
     * 프로필 아이디에 대해 팔로우가 이미 존재하는지 확인
     */
    boolean checkFollow(long followerId, long followeeId);
}
