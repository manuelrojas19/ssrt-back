package com.ipn.upiicsa.proy.sstr.timereportservice.util;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class JwtTokenUtil {

    @Value("${authentication.tokenSecret}")
    private String tokenSecret;

    public String getUsernameFromToken(String token) {
        log.info("Token ---> {}", token);
        Claims claims = Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public String getRoleFromToken(String token) {
        log.info("Token ---> {}", token);
        Claims claims = Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token).getBody();
        return claims.get("role", String.class);
    }

    public boolean validateToken(String token) {
        if (Objects.isNull(token)) return false;
        try {
            Jwts.parser().setSigningKey(tokenSecret).parse(token);
            return true;
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