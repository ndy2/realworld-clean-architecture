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

    private final FollowRepository followRepository;

    @Override
    public Optional<Long> checkFollow(long followerId, long followeeId) {
        return followRepository.findByFollowerIdEqualsAndFolloweeIdEquals(followerId, followeeId)
                .map(FollowJpaEntity::getId);
    }

    @Override
    public void insertFollow(long followerId, long followeeId) {
        followRepository.save(new FollowJpaEntity(followerId, followeeId));
    }

    @Override
    public void deleteById(long id) {
        FollowJpaEntity deleteTarget = followRepository.findById(id).orElseThrow(IllegalStateException::new);
        followRepository.delete(deleteTarget);
    }
}
