package com.deukyun.realworld.user.application.port.out;

import com.deukyun.realworld.user.domain.User.UserId;

public interface InsertUserPort {

    UserId insertUser(InsertUserCommand insertUserCommand);
}
