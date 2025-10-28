package com.code_crafters.app.controller;

import com.code_crafters.app.dto.request.LoginRequest;
import com.code_crafters.app.dto.request.RegisterRequest;
import com.code_crafters.app.dto.response.JwtResponse;
import com.code_crafters.app.entity.Users;
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

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest signUpRequest) {
        String result = usersService.register(signUpRequest);

        if (result.startsWith("Error")) {
            Map<String, String> response = new HashMap<>();
            response.put("message", result);
            return ResponseEntity.badRequest().body(response);
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", result);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Optional<Users> userOpt = usersService.login(loginRequest);

        if (userOpt.isPresent()) {
            Users user = userOpt.get();
            JwtResponse response = new JwtResponse(
                    "token-falso-por-ahora",
                    user.getName(),
                    user.getEmail()
            );
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Usuario o contraseña incorrecta");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
