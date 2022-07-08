package com.deukyun.realworld.profile.application.service;

import com.deukyun.realworld.profile.application.port.in.InsertProfileCommand;
import com.deukyun.realworld.profile.application.port.in.InsertProfileInPort;
import org.springframework.stereotype.Service;

@Service
public class InsertProfileService implements InsertProfileInPort {

    @Override
    public long insertProfile(InsertProfileCommand insertProfileCommand) {
        return 0;
    }
}
