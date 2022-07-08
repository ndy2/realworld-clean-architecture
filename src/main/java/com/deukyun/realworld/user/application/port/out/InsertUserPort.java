package com.deukyun.realworld.user.application.port.out;

import com.deukyun.realworld.user.domain.Email;
import com.deukyun.realworld.user.domain.Password;

public interface InsertUserPort {

    void insertUser(Email email, Password password);
}
