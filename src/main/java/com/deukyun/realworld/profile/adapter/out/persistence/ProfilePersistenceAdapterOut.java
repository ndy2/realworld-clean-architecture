package com.deukyun.realworld.profile.adapter.out.persistence;

import com.deukyun.realworld.common.component.PersistenceAdapter;
import com.deukyun.realworld.common.exception.RealworldRuntimeException;
import com.deukyun.realworld.profile.application.port.out.FindProfilePort;
import com.deukyun.realworld.profile.application.port.out.InsertProfileOutCommand;
import com.deukyun.realworld.profile.application.port.out.InsertProfileOutPort;
import com.deukyun.realworld.profile.application.port.out.ProfileOutResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
class ProfilePersistenceAdapterOut implements
        InsertProfileOutPort,
        FindProfilePort {

    private final ProfileRepository profileRepository;

    @Override
    public ProfileOutResponse findByUserId(long userId) {
        return profileRepository.findByUserId(userId).orElseThrow(RealworldRuntimeException::new);
    }

    @Override
    public void insertProfile(InsertProfileOutCommand insertProfileCommand) {
        profileRepository.save(
                new ProfileJpaEntity(
                        insertProfileCommand.getUsername(),
                        insertProfileCommand.getUserId()
                )
        );
    }
}
