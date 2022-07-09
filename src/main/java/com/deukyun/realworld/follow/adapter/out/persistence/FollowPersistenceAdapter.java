package com.deukyun.realworld.follow.adapter.out.persistence;

import com.deukyun.realworld.common.component.PersistenceAdapter;
import com.deukyun.realworld.follow.application.port.out.CheckFollowPort;
import com.deukyun.realworld.follow.application.port.out.DeleteFollowPort;
import com.deukyun.realworld.follow.application.port.out.InsertFollowPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@PersistenceAdapter
public class FollowPersistenceAdapter implements
        CheckFollowPort,
        InsertFollowPort,
        DeleteFollowPort {

    private final FollowJpaRepository followJpaRepository;

    @Override
    public Optional<Long> checkFollow(long followerId, long followeeId) {
        return followJpaRepository.findIdByFollowerIdEqualsAndFolloweeIdEquals(followerId, followeeId);
    }

    @Override
    public void insertFollow(long followerId, long followeeId) {
        followJpaRepository.save(new FollowJpaEntity(followerId, followeeId));
    }

    @Override
    public void deleteById(long id) {
        FollowJpaEntity deleteTarget = followJpaRepository.findById(id).orElseThrow(IllegalStateException::new);
        followJpaRepository.delete(deleteTarget);
    }
}
