package com.deukyun.realworld.follow.application.port.out;

public interface InsertFollowPort {

    void insertFollow(long followerId, long followeeId);
}
