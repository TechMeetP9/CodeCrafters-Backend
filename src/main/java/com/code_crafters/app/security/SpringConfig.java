package com.code_crafters.app.security;

import com.code_crafters.app.security.filter.JWTAuthentication;
import com.code_crafters.app.security.filter.JWTAuthorization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringConfig {
    
    private CustomAuthenticationManager customAuthenticationManager;
    
    public SpringConfig(CustomAuthenticationManager customAuthenticationManager) {
        this.customAuthenticationManager = customAuthenticationManager;
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        JWTAuthentication jwtAuthentication = new JWTAuthentication(customAuthenticationManager);
        jwtAuthentication.setFilterProcessesUrl("/api/auth/login");
        
        http
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable())) 
            .authorizeHttpRequests(request -> request
                .requestMatchers("/h2-console/**").permitAll() 
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/events/**").permitAll() 
                .requestMatchers(HttpMethod.GET, "/api/categories/**").permitAll() 
                .requestMatchers(HttpMethod.GET, "/api/locations/**").permitAll() 
                .requestMatchers("/").permitAll() 
                
                // Rutas que requieren autenticación
                .requestMatchers(HttpMethod.POST, "/api/events/**").authenticated() 
                .requestMatchers(HttpMethod.PUT, "/api/events/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/events/**").authenticated() 
                .requestMatchers("/api/attendance/**").authenticated() 
                .requestMatchers("/api/users/profile/**").authenticated() 
                
                .anyRequest().authenticated()
            )
            .addFilter(jwtAuthentication)
            .addFilterAfter(new JWTAuthorization(), JWTAuthentication.class)
            .sessionManagement(management -> 
                management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );
        
        return http.build();
    }
}