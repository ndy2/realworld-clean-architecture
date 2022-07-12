package com.deukyun.realworld.configuration;

import com.deukyun.realworld.user.application.port.in.CustomPasswordEncoder;
import com.deukyun.realworld.user.domain.Password;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfiguration {

    @Bean
    public CustomPasswordEncoder passwordEncoder() {

        return new CustomPasswordEncoder() {

            private final PasswordEncoder target = new BCryptPasswordEncoder();

            @Override
            public Password encode(Password rawPassword) {
                return new Password(target.encode(rawPassword.getValue()));
            }

            @Override
            public boolean matches(Password rawPassword, Password encodedPassword) {
                return target.matches(rawPassword.getValue(), encodedPassword.getValue());
            }
        };
    }

}
