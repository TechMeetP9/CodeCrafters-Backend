package com.code_crafters.app.security;

import com.code_crafters.app.service.interfaces.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {
    
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public CustomAuthenticationManager(UserService userService, 
                                      BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails user = userService.loadUserByUsername(authentication.getName());
        
        if (!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), 
                                           user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }
        
        return new UsernamePasswordAuthenticationToken(
            authentication.getName(), 
            user.getPassword(), 
            user.getAuthorities()
        );
    }
}