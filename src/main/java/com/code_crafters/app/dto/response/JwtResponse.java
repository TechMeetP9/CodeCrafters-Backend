package com.code_crafters.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String username;
    private String name;
    
    public JwtResponse(String token, String username, String name) {
        this.token = token;
        this.username = username;
        this.name = name;
    }
}