package com.deukyun.realworld.follow.application.service;

import com.deukyun.realworld.common.component.UseCase;
import com.deukyun.realworld.follow.application.port.in.FollowUserUseCases;
import com.deukyun.realworld.follow.application.port.in.dto.query.FollowUserResult;
import com.deukyun.realworld.follow.application.port.out.CheckFollowPort;
import com.deukyun.realworld.follow.application.port.out.DeleteFollowPort;
import com.deukyun.realworld.follow.application.port.out.InsertFollowPort;
import com.deukyun.realworld.profile.application.port.out.FindProfileByUserIdPort;
import com.deukyun.realworld.profile.application.port.out.FindProfileByUsernamePort;
import com.deukyun.realworld.profile.application.port.out.dto.query.FindProfileByUserIdResult;
import com.deukyun.realworld.profile.application.port.out.dto.query.FindProfileByUsernameResult;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

@RequiredArgsConstructor
@UseCase
class FollowUserService implements
        FollowUserUseCases {

    private final FindProfileByUserIdPort findProfileByUserIdPort;
    private final FindProfileByUsernamePort findProfileByUsernamePort;
    private final CheckFollowPort checkFollowPort;
    private final InsertFollowPort insertFollowPort;
    private final DeleteFollowPort deleteFollowPort;

    /**
     * 팔로워가 팔로이를 팔로우 함
     * 이미 팔로우 중이거나, 자기자신에 대한 팔로우 요청일 경우 예외를 던짐
     */
    @Transactional
    @Override
    public FollowUserResult userIdFollowsUsername(long userId, String username) {
        FindProfileByUserIdResult followerProfile = findProfileByUserIdPort.findByUserId(userId);
        FindProfileByUsernameResult followeeProfile = findProfileByUsernamePort.findProfileByUsername(username);

        long followerProfileId = followerProfile.getId();
        long followeeProfileId = followeeProfile.getId();

        checkFollowable(followerProfileId, followeeProfileId);
        insertFollowPort.insertFollow(followerProfileId, followeeProfileId);

        return new FollowUserResult(
                followeeProfile.getUsername(),
                followeeProfile.getBio(),
                followeeProfile.getImage(),
                true
        );
    }

    private void checkFollowable(long followerProfileId, long followeeProfileId) {
        boolean isAlreadyFollow = checkFollowPort.checkFollow(
                followerProfileId,
                followeeProfileId
        ).isPresent();

        checkArgument(!isAlreadyFollow, "이미 팔로우 중입니다");
        checkArgument(followerProfileId != followeeProfileId, "자기 자신을 팔로우 할 수 없습니다");
    }

    /**
     * 팔로워가 팔로이를 언팔로우 함
     * 팔로우 중이 아니라면 예외를 던짐
     */
    @Transactional
    @Override
    public FollowUserResult userIdUnfollowsUsername(long userId, String username) {
        FindProfileByUserIdResult followerProfile = findProfileByUserIdPort.findByUserId(userId);
        FindProfileByUsernameResult followeeProfile = findProfileByUsernamePort.findProfileByUsername(username);

        long followerProfileId = followerProfile.getId();
        long followeeProfileId = followeeProfile.getId();

        long deleteTargetId = checkUnfollowable(followerProfileId, followeeProfileId);
        deleteFollowPort.deleteById(deleteTargetId);

        return new FollowUserResult(
                followeeProfile.getUsername(),
                followeeProfile.getBio(),
                followeeProfile.getImage(),
                false
        );
    }

    private long checkUnfollowable(long followerProfileId, long followeeProfileId) {
        Optional<Long> idIfUnfollowable = checkFollowPort.checkFollow(
                followerProfileId,
                followeeProfileId
        );

        checkArgument(idIfUnfollowable.isPresent(), "언팔로우 할 수 없습니다");

        return idIfUnfollowable.get();
    }
}
