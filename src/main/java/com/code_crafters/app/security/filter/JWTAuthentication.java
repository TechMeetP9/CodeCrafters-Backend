package com.code_crafters.app.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.code_crafters.app.dto.response.JwtResponse;
import com.code_crafters.app.entity.User;
import com.code_crafters.app.security.CustomAuthenticationManager;
import com.code_crafters.app.security.SecurityConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JWTAuthentication extends UsernamePasswordAuthenticationFilter {
    
    private CustomAuthenticationManager customAuthenticationManager;
    
    public JWTAuthentication(CustomAuthenticationManager customAuthenticationManager) {
        this.customAuthenticationManager = customAuthenticationManager;
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, 
                                               HttpServletResponse response) 
                                               throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getUsername(), 
                user.getPassword()
            );
            return customAuthenticationManager.authenticate(authentication);
        } catch (Exception e) {
            throw new RuntimeException("Error processing authentication", e);
        }
    }
    
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, 
                                             HttpServletResponse response, 
                                             AuthenticationException failed) 
                                             throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"" + failed.getMessage() + "\"}");
        response.getWriter().flush();
    }
    
    @Override
    protected void successfulAuthentication(HttpServletRequest request, 
                                           HttpServletResponse response, 
                                           FilterChain chain, 
                                           Authentication authResult) 
                                           throws IOException, ServletException {
        List<String> roles = authResult.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .map(role -> role.replace("ROLE_", ""))
            .collect(Collectors.toList());
        
        String token = JWT.create()
            .withSubject(authResult.getName())
            .withClaim("roles", roles)
            .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
            .sign(Algorithm.HMAC512(SecurityConstants.SECRET_KEY));
        
        JwtResponse jwtResponse = new JwtResponse(
            token,
            authResult.getName(),
            authResult.getName()
        );
        
        response.setContentType("application/json");
        response.addHeader("Authorization", "Bearer " + token);
        response.getWriter().write(new ObjectMapper().writeValueAsString(jwtResponse));
        response.getWriter().flush();
    }
}