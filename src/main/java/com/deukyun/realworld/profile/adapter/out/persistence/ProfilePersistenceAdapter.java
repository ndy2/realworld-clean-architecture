package com.deukyun.realworld.profile.adapter.out.persistence;

import com.deukyun.realworld.common.component.PersistenceAdapter;
import com.deukyun.realworld.common.exception.RealworldRuntimeException;
import com.deukyun.realworld.profile.application.port.out.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
class ProfilePersistenceAdapter implements
        InsertProfilePort,
        FindProfilePort,
        UpdateProfilePort,
        FindProfileByUsernamePort {

    private final ProfileRepository profileRepository;

    @Override
    public FindProfileResult findByUserId(long userId) {
        return profileRepository.findByUserIdProjection(userId).orElseThrow(RealworldRuntimeException::new);
    }

    @Override
    public void insertProfile(InsertProfileCommand insertProfileCommand) {
        profileRepository.save(
                new ProfileJpaEntity(
                        insertProfileCommand.getUsername(),
                        insertProfileCommand.getUserId()
                )
        );
    }

    @Override
    public UpdateProfileResult updatePort(UpdateProfileCommand updateProfileCommand) {

        ProfileJpaEntity profileJpaEntity =
                profileRepository.findByUserId(updateProfileCommand.getUserId()).orElseThrow(RealworldRuntimeException::new);

        profileJpaEntity.update(
                updateProfileCommand.getUsername(),
                updateProfileCommand.getBio(),
                updateProfileCommand.getImage()
        );

        return new UpdateProfileResult(
                profileJpaEntity.getUsername(),
                profileJpaEntity.getBio(),
                profileJpaEntity.getImage()
        );
    }

    @Override
    public FindProfileByUsernameResult findProfileByUsername(String username) {
        return profileRepository.findByUsernameProjection(username).orElseThrow(RealworldRuntimeException::new);
    }
}
