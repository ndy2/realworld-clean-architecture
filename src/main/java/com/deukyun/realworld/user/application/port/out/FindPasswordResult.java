package com.deukyun.realworld.user.application.port.out;


import com.deukyun.realworld.user.domain.Password;
import com.deukyun.realworld.user.domain.User.UserId;
import lombok.Value;

@Value
public class FindPasswordResult {

    UserId userId;
    Password password;

    public FindPasswordResult(UserId userId, Password password) {
        this.userId = userId;
        this.password = password;
    }

    /**
     * jpql 바인딩을 위한 생성자
     */
    public FindPasswordResult(long userId, String password) {
        this(new UserId(userId), new Password(password));
    }
}
