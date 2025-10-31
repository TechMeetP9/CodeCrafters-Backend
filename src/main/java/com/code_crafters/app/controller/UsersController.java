package com.code_crafters.app.controller;

import com.code_crafters.app.dto.request.UpdateUserRequest;
import com.code_crafters.app.dto.response.UsersResponse;
import com.code_crafters.app.entity.User;
import com.code_crafters.app.mapper.UsersMapper;
import com.code_crafters.app.service.interfaces.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private UsersMapper usersMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UsersResponse> getUserById(@PathVariable UUID id) {
        User user = usersService.findById(id)
                .orElseThrow(() -> new com.code_crafters.app.exception.ResourceNotFoundException("User not found"));
        return ResponseEntity.ok(usersMapper.toDto(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsersResponse> updateUser(@PathVariable UUID id,
                                                    @RequestBody UpdateUserRequest request) {
        User updatedUser = usersService.updateUser(id, request);
        return ResponseEntity.ok(usersMapper.toDto(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        usersService.deleteUser(id);
        return ResponseEntity.ok().body(Map.of("message", "User deleted successfully"));
    }
}
