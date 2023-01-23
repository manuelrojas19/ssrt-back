package com.ipn.upiicsa.proy.sstr.timereportservice.config;

import com.ipn.upiicsa.proy.sstr.timereportservice.util.JwtTokenUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Slf4j
public class AuthFilter extends OncePerRequestFilter {

    private static final String AUTH_HEADER_NAME = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String INVALID_TOKEN_ERROR_MSG = "The authorization token is invalid";

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String token = getJwtFromRequest(request);

        if (Objects.nonNull(token) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {

            if (!jwtTokenUtil.validateToken(token))
                throw new IllegalArgumentException(INVALID_TOKEN_ERROR_MSG);

            String username = jwtTokenUtil.getUsernameFromToken(token);
            Collection<SimpleGrantedAuthority> role = Collections
                    .singletonList(new SimpleGrantedAuthority(jwtTokenUtil.getRoleFromToken(token)));

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, role);

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTH_HEADER_NAME);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)) {
            bearerToken = bearerToken.substring(7);
        }
        return bearerToken;
    }
}