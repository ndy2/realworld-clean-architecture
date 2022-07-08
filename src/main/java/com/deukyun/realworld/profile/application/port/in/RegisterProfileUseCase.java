package com.deukyun.realworld.profile.application.port.in;

public interface RegisterProfileUseCase {

    /**
     * 회원 가입시 사용함 <br>
     * username 을 받아서 프로필을 삽입한다
     */
    void registerProfile(RegisterProfileCommand registerProfileCommand);
}
