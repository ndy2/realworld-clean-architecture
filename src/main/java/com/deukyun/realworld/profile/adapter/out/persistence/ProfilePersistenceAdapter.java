package com.deukyun.realworld.profile.adapter.out.persistence;

import com.deukyun.realworld.common.component.PersistenceAdapter;
import com.deukyun.realworld.common.exception.RealworldRuntimeException;
import com.deukyun.realworld.profile.application.port.out.*;
import com.deukyun.realworld.profile.domain.Profile.ProfileId;
import com.deukyun.realworld.user.domain.Email;
import com.deukyun.realworld.user.domain.User.UserId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
class ProfilePersistenceAdapter implements
        InsertProfilePort,
        FindProfileByUserIdPort,
        FindProfileIdByUserIdPort,
        UpdateProfilePort,
        FindProfileByUsernamePort {

    private final ProfileRepository profileRepository;

    @Override
    public FindProfileByUserIdResult findByUserId(UserId userId) {
        ProfileJpaEntity profileJpaEntity
                = profileRepository.findByUserIdProjection(userId.getValue()).orElseThrow(RealworldRuntimeException::new);

        return new FindProfileByUserIdResult(
                new ProfileId(profileJpaEntity.getId()),
                new Email(profileJpaEntity.getUserEmail()),
                profileJpaEntity.getUsername(),
                profileJpaEntity.getBio(),
                profileJpaEntity.getImage()
        );
    }

    @Override
    public ProfileId findProfileIdByUserId(UserId userId) {
        return new ProfileId(profileRepository.findIdByUserId(userId.getValue()).orElseThrow(RealworldRuntimeException::new));
    }

    @Override
    public void insertProfile(InsertProfileCommand insertProfileCommand) {
        profileRepository.save(
                new ProfileJpaEntity(
                        insertProfileCommand.getUsername(),
                        insertProfileCommand.getUserId().getValue()
                )
        );
    }

    @Override
    public UpdateProfileResult updatePort(UpdateProfileCommand updateProfileCommand) {

        UserId userId = updateProfileCommand.getUserId();
        ProfileJpaEntity profileJpaEntity =
                profileRepository.findByUserId(userId.getValue()).orElseThrow(RealworldRuntimeException::new);

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
