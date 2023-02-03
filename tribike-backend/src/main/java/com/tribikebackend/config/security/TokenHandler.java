package com.tribikebackend.config.security;

import com.tribikebackend.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class TokenHandler implements Serializable {

    private static final String KEY_TOKEN = "keytoken";

    private final int MILIS_IN_A_SECOND = 1000;
    private Clock clock = DefaultClock.INSTANCE;


    @Value("${token.expiration.hours:-1}")
    private int hoursToExpire;

    @Value("${jwt.expiration}")
    private Long expiration;

    private final Logger log = LoggerFactory.getLogger(TokenHandler.class);

    public Usuario parseUserFromToken(String token) {
        Claims body = Jwts.parser()
                .setSigningKey(KEY_TOKEN)
                .parseClaimsJws(token)
                .getBody();
        String email = body.getSubject();
        Integer role = body.get("role", Integer.class);
        
        return Usuario
            .builder()
            .email(email)
            .papel(role)
            .build();
    }

    public String createTokenForUser(Usuario user) {
        final LocalDateTime createdDate = LocalDateTime.now();
        final Date expirationDate = calculateExpirationDate(createdDate);

        log.info("O token criado exipra em: " + expirationDate.toString());
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setExpiration(expirationDate)
                .claim("role", user.getPapel())
                .signWith(SignatureAlgorithm.HS256, KEY_TOKEN)
                .compact();
    }
    private Date calculateExpirationDate(LocalDateTime createdDate) {
        Date expirationDate = null;
        if(expiration > 0){
            LocalDateTime ExpirationLocalDateTime = createdDate.plusSeconds(expiration);
            expirationDate =  Date.from(ExpirationLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());
        }
        return expirationDate;
    }
}

