package com.code_crafters.app.controller;

import com.code_crafters.app.dto.request.LoginRequest;
import com.code_crafters.app.dto.request.RegisterRequest;
import com.code_crafters.app.dto.response.JwtResponse;
import com.code_crafters.app.entity.User;
import com.code_crafters.app.mapper.UsersMapper;
import com.code_crafters.app.service.interfaces.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private UsersMapper usersMapper;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest signUpRequest) {
        try {
            User savedUser = usersService.register(signUpRequest);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "User registered successfully");
            response.put("user", usersMapper.toDto(savedUser)); 

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {

            Map<String, String> response = new HashMap<>();
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
        
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error registering user");
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOpt = usersService.login(loginRequest);

        if (userOpt.isPresent()) {
            JwtResponse jwtResponse = usersMapper.toJwtResponse(userOpt.get());
            return ResponseEntity.ok(jwtResponse);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Invalid username or password");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
