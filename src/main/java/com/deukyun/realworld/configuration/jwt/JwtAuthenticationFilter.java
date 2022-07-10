package com.deukyun.realworld.configuration.jwt;

import com.deukyun.realworld.common.SecurityUser;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Objects;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.util.StringUtils.hasText;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtResolver jwtResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            String token = getToken(request);

            if (token != null) {
                String id = jwtResolver.getClaims(token, Claims::getId);

                SecurityContextHolder.getContext()
                        .setAuthentication(new UsernamePasswordAuthenticationToken(new SecurityUser(Long.parseLong(id), token), null, Collections.emptySet()));
            }

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION);

        if (hasText(token)) {
            log.debug("Jwt authorization api detected: {}", token);

            return URLDecoder.decode(token, StandardCharsets.UTF_8);
        }
        return null;
    }

    private boolean isNotExistsUserDetails(final UserDetails userDetails) {
        return Objects.isNull(userDetails);
    }
}