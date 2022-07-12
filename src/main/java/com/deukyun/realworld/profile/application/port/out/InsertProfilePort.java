package com.deukyun.realworld.profile.application.port.out;

import com.deukyun.realworld.profile.application.port.out.dto.command.InsertProfileCommand;

public interface InsertProfilePort {

    void insertProfile(InsertProfileCommand insertProfileCommand);
}
