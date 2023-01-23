package com.ipn.upiicsa.proy.sstr.userservice.util;

import com.ipn.upiicsa.proy.sstr.userservice.entity.User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

@Slf4j
@Component
public class JwtTokenUtil {

    @Value("${authentication.tokenSecret}")
    private String tokenSecret;

    @Value("${authentication.tokenExpirationMsec}")
    private Long tokenExpirationMsec;


    public Token generateAccessToken(User user) {
        Date now = new Date();
        long duration = now.getTime() + tokenExpirationMsec;
        Date expiryDate = new Date(duration);
        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .claim("role",  user.getAuthorities().stream().findFirst().get().getAuthority())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, tokenSecret)
                .compact();
        return new Token(Token.TokenType.ACCESS, token, duration,
                LocalDateTime.ofInstant(expiryDate.toInstant(), ZoneId.systemDefault()));
    }

    public String getUsernameFromToken(String token) {
        log.info("Token ---> {}", token);
        Claims claims = Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public LocalDateTime getExpiryDateFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token).getBody();
        return LocalDateTime.ofInstant(claims.getExpiration().toInstant(), ZoneId.systemDefault());
    }

    public boolean validateToken(String token) {
        if (Objects.isNull(token)) return false;
        try {
            Jwts.parser().setSigningKey(tokenSecret).parse(token);
            return true;
        } catch (SignatureException ex) {
            log.info("SignatureException");
            ex.printStackTrace();
        } catch (MalformedJwtException ex) {
            log.info("MalformedJwtException");
            ex.printStackTrace();
        } catch (ExpiredJwtException ex) {
            log.info("ExpiredJwtException");
            ex.printStackTrace();
        } catch (UnsupportedJwtException ex) {
            log.info("UnsupportedJwtException");
            ex.printStackTrace();
        } catch (IllegalArgumentException ex) {
            log.info("IllegalArgumentException");
            ex.printStackTrace();
        }
        return false;
    }
}