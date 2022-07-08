package com.deukyun.realworld.user.application.port.out;

public interface InsertUserPort {

    long insertUser(InsertUserCommand insertUserCommand);
}
