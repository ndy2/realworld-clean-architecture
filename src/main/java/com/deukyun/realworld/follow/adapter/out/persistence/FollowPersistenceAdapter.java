package com.deukyun.realworld.follow.adapter.out.persistence;

import com.deukyun.realworld.common.component.PersistenceAdapter;
import com.deukyun.realworld.follow.application.port.out.CheckFollowPort;
import com.deukyun.realworld.follow.application.port.out.DeleteFollowPort;
import com.deukyun.realworld.follow.application.port.out.InsertFollowPort;
import com.deukyun.realworld.profile.domain.Profile.ProfileId;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@PersistenceAdapter
public class FollowPersistenceAdapter implements
        CheckFollowPort,
        InsertFollowPort,
        DeleteFollowPort {

    private final FollowRepository followRepository;

    @Override
    public Optional<ProfileId> checkFollow(ProfileId followerId, ProfileId followeeId) {
        return followRepository.findByFollowerIdEqualsAndFolloweeIdEquals(followerId.getValue(), followeeId.getValue())
                .map(FollowJpaEntity::getId)
                .map(ProfileId::new);
    }

    @Override
    public List<Boolean> checkFollows(ProfileId followerId, List<ProfileId> followeeIds) {

        List<Long> foundFolloweeIds = followRepository.findFolloweeIdsByFollowerId(followerId.getValue());

        return followeeIds.stream()
                .map(ProfileId::getValue)
                .map(id -> foundFolloweeIds.stream().anyMatch(id::equals)).collect(toList());
    }

    @Override
    public void insertFollow(ProfileId followerId, ProfileId followeeId) {
        followRepository.save(new FollowJpaEntity(followerId.getValue(), followeeId.getValue()));
    }

    @Override
    public void deleteById(ProfileId id) {
        FollowJpaEntity deleteTarget = followRepository.findById(id.getValue()).orElseThrow(IllegalStateException::new);
        followRepository.delete(deleteTarget);
    }
}
