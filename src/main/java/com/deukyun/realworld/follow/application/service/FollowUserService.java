package com.deukyun.realworld.follow.application.service;

import com.deukyun.realworld.common.component.UseCase;
import com.deukyun.realworld.follow.application.port.out.CheckFollowPort;
import com.deukyun.realworld.follow.application.port.out.InsertFollowPort;
import com.deukyun.realworld.profile.application.port.in.FollowUserResult;
import com.deukyun.realworld.profile.application.port.in.FollowUserUseCase;
import com.deukyun.realworld.profile.application.port.out.FindProfileByUserIdPort;
import com.deukyun.realworld.profile.application.port.out.FindProfileByUserIdResult;
import com.deukyun.realworld.profile.application.port.out.FindProfileByUsernamePort;
import com.deukyun.realworld.profile.application.port.out.FindProfileByUsernameResult;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import static com.google.common.base.Preconditions.checkArgument;

@RequiredArgsConstructor
@UseCase
public class FollowUserService implements FollowUserUseCase {

    private final FindProfileByUserIdPort findProfileByUserIdPort;
    private final FindProfileByUsernamePort findProfileByUsernamePort;
    private final CheckFollowPort checkFollowPort;
    private final InsertFollowPort insertFollowPort;

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
        );

        checkArgument(isAlreadyFollow, "이미 팔로우 중입니다");
        checkArgument(followerProfileId == followeeProfileId, "자기 자신을 팔로우 할 수 없습니다");
    }
}
