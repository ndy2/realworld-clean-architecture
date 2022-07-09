package com.deukyun.realworld.follow.adapter.out.persistence;

import com.deukyun.realworld.common.component.PersistenceAdapter;
import com.deukyun.realworld.follow.application.port.out.CheckFollowPort;
import com.deukyun.realworld.follow.application.port.out.InsertFollowPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class FollowPersistenceAdapter implements
        CheckFollowPort,
        InsertFollowPort {

    private final FollowJpaRepository followJpaRepository;

    @Override
    public boolean checkFollow(long followerId, long followeeId) {
        return followJpaRepository.existsByFollowerIdEqualsAndFolloweeIdEquals(followerId, followeeId);
    }

    @Override
    public void insertFollow(long followerId, long followeeId) {
        followJpaRepository.save(new FollowJpaEntity(followerId, followeeId));
    }
}
