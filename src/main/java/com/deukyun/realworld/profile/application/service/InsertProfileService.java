package com.deukyun.realworld.profile.application.service;

import com.deukyun.realworld.profile.application.port.in.InsertProfileCommand;
import com.deukyun.realworld.profile.application.port.in.InsertProfileInPort;
import com.deukyun.realworld.profile.application.port.out.InsertProfileOutCommand;
import com.deukyun.realworld.profile.application.port.out.InsertProfileOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 아웃 포트로 위임만 함
 */
@RequiredArgsConstructor
@Service
public class InsertProfileService implements InsertProfileInPort {

    private final InsertProfileOutPort insertProfileOutPort;

    @Override
    public void insertProfile(InsertProfileCommand insertProfileInCommand) {
        insertProfileOutPort.insertProfile(
                new InsertProfileOutCommand(
                        insertProfileInCommand.getUserId(),
                        insertProfileInCommand.getUsername()
                )
        );
    }
}
