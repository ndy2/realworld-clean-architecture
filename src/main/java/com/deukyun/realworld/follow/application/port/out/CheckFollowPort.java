package com.deukyun.realworld.follow.application.port.out;

import com.deukyun.realworld.profile.domain.Profile.ProfileId;

import java.util.List;
import java.util.Optional;

public interface CheckFollowPort {

    /**
     * 프로필 아이디에 대해 팔로우가 이미 존재하는지 확인
     * <p>
     * 존재한다면 id 리턴, 존재하지 않으면 null 리턴
     */
    Optional<ProfileId> checkFollow(ProfileId followerId, ProfileId followeeId);

    /**
     * follower 가 followeeId 목록에 대하여
     * 팔로우 여부를 리스트로 반환
     */
    List<Boolean> checkFollows(ProfileId followerId, List<ProfileId> followeeIds);
}
