package com.deukyun.realworld.profile.adapter.out.persistence;

import com.deukyun.realworld.common.component.PersistenceAdapter;
import com.deukyun.realworld.common.exception.RealworldRuntimeException;
import com.deukyun.realworld.profile.application.port.out.FindProfilePort;
import com.deukyun.realworld.profile.application.port.out.InsertProfileCommand;
import com.deukyun.realworld.profile.application.port.out.InsertProfilePort;
import com.deukyun.realworld.profile.application.port.out.ProfileOutResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
class ProfilePersistenceAdapter implements
        InsertProfilePort,
        FindProfilePort {

    private final ProfileRepository profileRepository;

    @Override
    public ProfileOutResponse findByUserId(long userId) {
        return profileRepository.findByUserId(userId).orElseThrow(RealworldRuntimeException::new);
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
}
