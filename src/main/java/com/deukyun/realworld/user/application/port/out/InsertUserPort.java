package com.deukyun.realworld.user.application.port.out;

import com.deukyun.realworld.user.application.port.out.dto.command.InsertUserCommand;

public interface InsertUserPort {

    long insertUser(InsertUserCommand insertUserCommand);
}
