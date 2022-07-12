package com.deukyun.realworld.user.application.service;

import com.deukyun.realworld.user.application.port.out.FindPasswordPort;
import com.deukyun.realworld.user.application.port.out.dto.query.FindPasswordResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthenticationServiceTest {

    AuthenticationService authenticationService;

    FindPasswordPort findPasswordPort = mock(FindPasswordPort.class);
    PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);

    @BeforeEach
    void setUp() {
        when(findPasswordPort.findPasswordByEmail("jake@jake.jake")).thenReturn(
                Optional.of(new FindPasswordResult(1L, "[encoded]jakejake"))
        );
        when(passwordEncoder.matches("jakejake", "[encoded]jakejake")).thenReturn(true);

        authenticationService = new AuthenticationService(
                findPasswordPort,
                passwordEncoder
        );
    }

    @Test
    void 인증_성공() {
        //given
        String email = "jake@jake.jake";
        String password = "jakejake";

        //when
        long result = authenticationService.authenticate(email, password);

        //then
        assertThat(result).isEqualTo(1L);
    }

    @Test
    void 이메일이_없으면_실패() {
        //given
        String email = "invalid@email.com";
        String password = "invalidPassword";

        //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> authenticationService.authenticate(email, password));
    }

    @Test
    void 이메일이_맞아도_비밀번호가_틀리면_실패() {
        //given
        String email = "jake@jake.jake";
        String password = "invalidPassword";

        //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> authenticationService.authenticate(email, password));
    }
}