package com.deukyun.realworld.user.application.service;

import com.deukyun.realworld.user.application.port.in.CustomPasswordEncoder;
import com.deukyun.realworld.user.application.port.out.FindPasswordPort;
import com.deukyun.realworld.user.application.port.out.FindPasswordResult;
import com.deukyun.realworld.user.domain.Email;
import com.deukyun.realworld.user.domain.Password;
import com.deukyun.realworld.user.domain.User.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthenticationServiceTest {

    AuthenticationService authenticationService;

    FindPasswordPort findPasswordPort = mock(FindPasswordPort.class);
    CustomPasswordEncoder passwordEncoder = mock(CustomPasswordEncoder.class);

    @BeforeEach
    void setUp() {
        when(findPasswordPort.findPasswordByEmail(new Email("jake@jake.jake"))).thenReturn(
                Optional.of(new FindPasswordResult(new UserId(1L), new Password("[encoded]jakejake")))
        );
        when(passwordEncoder.matches(
                new Password("jakejake"),
                new Password("[encoded]jakejake"))
        )
                .thenReturn(true);

        authenticationService = new AuthenticationService(
                findPasswordPort,
                passwordEncoder
        );
    }

    @Test
    void 인증_성공() {
        //given
        Email email = new Email("jake@jake.jake");
        Password password = new Password("jakejake");

        //when
        UserId result = authenticationService.authenticate(email, password);

        //then
        assertThat(result).isEqualTo(new UserId(1L));
    }

    @Test
    void 이메일이_없으면_실패() {
        //given
        Email email = new Email("invalid@email.com");
        Password password = new Password("invalidPassword");

        //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> authenticationService.authenticate(email, password));
    }

    @Test
    void 이메일이_맞아도_비밀번호가_틀리면_실패() {
        //given
        Email email = new Email("jake@jake.jake");
        Password password = new Password("invalidPassword");

        //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> authenticationService.authenticate(email, password));
    }
}