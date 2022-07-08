package com.deukyun.realworld.profile.application.port.in;

public interface InsertProfileInPort {

    /**
     * 회원 가입시 사용함 <br>
     * username 을 받아서 프로필을 삽입한다
     */
    void insertProfile(InsertProfileCommand insertProfileCommand);
}
