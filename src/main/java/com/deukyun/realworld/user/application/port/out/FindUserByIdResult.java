package com.deukyun.realworld.user.application.port.out;

import com.deukyun.realworld.user.domain.Email;
import lombok.Value;

@Value
public class FindUserByIdResult {

    Email email;

    /**
     * jpql 바인딩을 위한 생성자
     */
    public FindUserByIdResult(String email) {
        this.email = new Email(email);
    }
}
