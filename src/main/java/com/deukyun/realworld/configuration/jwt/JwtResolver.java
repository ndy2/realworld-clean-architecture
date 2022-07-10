package com.deukyun.realworld.configuration.jwt;

import com.deukyun.realworld.common.exception.RealworldRuntimeException;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtResolver {

    private final JwtProperties jwtProperties;

    public String generate(String id) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + jwtProperties.getExpiration());

        return Jwts.builder()
                .setSubject("Real World API Token")
                .setIssuer("Real World API")
                .setIssuedAt(now)
                .setId(id)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    public <R> R getClaims(final String token, Function<Claims, R> claimsResolver) {
        final Claims claims = parseClaimsJws(token);
        return claimsResolver.apply(claims);
    }

    private Claims parseClaimsJws(final String token) {
        try {
            final String secretKey = jwtProperties.getSecretKey();
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new RealworldRuntimeException("인증이 만료되었습니다.");
        } catch (UnsupportedJwtException e) {
            throw new RealworldRuntimeException("지원하지 않는 형식입니다.");
        } catch (MalformedJwtException e) {
            throw new RealworldRuntimeException("잘못된 형식이 포함 되었습니다.");
        } catch (IllegalArgumentException e) {
            throw new RealworldRuntimeException("유효하지 않은 값이 포함 되었습니다.");
        }
    }
}
