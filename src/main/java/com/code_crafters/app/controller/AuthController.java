package com.code_crafters.app.controller;

import com.code_crafters.app.dto.request.LoginRequest;
import com.code_crafters.app.dto.request.RegisterRequest;
import com.code_crafters.app.dto.response.JwtResponse;
import com.code_crafters.app.entity.User;
import com.code_crafters.app.mapper.UsersMapper;
import com.code_crafters.app.security.JwtUtils;
import com.code_crafters.app.security.UsersDetailsImpl;
import com.code_crafters.app.service.interfaces.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"}, allowCredentials = "true")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsersService usersService;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private JwtUtils jwtUtils;

    // 🔹 REGISTRO
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
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );


            SecurityContextHolder.getContext().setAuthentication(authentication);


            String jwt = jwtUtils.generateJwtToken(authentication);

            UsersDetailsImpl userDetails = (UsersDetailsImpl) authentication.getPrincipal();

            JwtResponse jwtResponse = new JwtResponse(
                     jwt,
                    userDetails.getUsername(),
                    userDetails.getEmail()
            );

            return ResponseEntity.ok(jwtResponse);

        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Invalid username or password");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
