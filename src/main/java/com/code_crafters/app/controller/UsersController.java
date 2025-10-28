package com.code_crafters.app.controller;

import com.code_crafters.app.entity.Users;
import com.code_crafters.app.service.interfaces.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<Users> userOpt = usersService.findById(id);
        if (userOpt.isPresent()) {
            return ResponseEntity.ok(userOpt.get());
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Usuario no encontrado");
            return ResponseEntity.status(404).body(response);
        }
    }
}
