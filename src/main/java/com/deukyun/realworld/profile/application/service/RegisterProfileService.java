package com.deukyun.realworld.profile.application.service;

import com.deukyun.realworld.common.component.UseCase;
import com.deukyun.realworld.profile.application.port.in.RegisterProfileCommand;
import com.deukyun.realworld.profile.application.port.in.RegisterProfileUseCase;
import com.deukyun.realworld.profile.application.port.out.InsertProfileOutCommand;
import com.deukyun.realworld.profile.application.port.out.InsertProfilePort;
import lombok.RequiredArgsConstructor;

/**
 * 아웃 포트로 위임만 함
 */
@RequiredArgsConstructor
@UseCase
class RegisterProfileService implements RegisterProfileUseCase {

    private final InsertProfilePort insertProfilePort;

    @Override
    public void registerProfile(RegisterProfileCommand registerProfileCommand) {
        insertProfilePort.insertProfile(
                new InsertProfileOutCommand(
                        registerProfileCommand.getUserId(),
                        registerProfileCommand.getUsername()
                )
        );
    }
}
