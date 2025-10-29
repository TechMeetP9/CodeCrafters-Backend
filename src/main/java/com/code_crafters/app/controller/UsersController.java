package com.code_crafters.app.controller;

import com.code_crafters.app.dto.request.UpdateUserRequest;
import com.code_crafters.app.dto.response.UsersResponse;
import com.code_crafters.app.entity.Users;
import com.code_crafters.app.mapper.UsersMapper;
import com.code_crafters.app.service.interfaces.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private UsersMapper usersMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UsersResponse> getUserById(@PathVariable Long id) {
        Users user = usersService.findById(id)
                .orElseThrow(() -> new com.code_crafters.app.exception.ResourceNotFoundException("User not found"));
        return ResponseEntity.ok(usersMapper.toDto(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsersResponse> updateUser(@PathVariable Long id,
                                                    @RequestBody UpdateUserRequest request) {
        Users updatedUser = usersService.updateUser(id, request);
        return ResponseEntity.ok(usersMapper.toDto(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        usersService.deleteUser(id);
        return ResponseEntity.ok().body(
                java.util.Map.of("message", "User deleted successfully")
        );
    }
}
