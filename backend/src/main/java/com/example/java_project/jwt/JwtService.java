package com.example.java_project.jwt;

import com.example.java_project.secret.Secret;
import com.example.java_project.src.user.model.UserEntity;
import com.example.java_project.utils.BaseException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.time.Instant;

@Service
public class JwtService {

    // jwt 생성
    public String createJWT(UserEntity userEntity) {
        Date expireDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, Secret.JWT_SECRET_KEY)
                .setSubject(userEntity.getIdx())
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .compact();

    }

    // jwt 유효성 검증, UserIdx 반환
    public String validateAndGetUserIdx(String token) {
            Claims claims = Jwts.parser()
                    .setSigningKey(Secret.JWT_SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
    }

}
