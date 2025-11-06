package com.code_crafters.app.controller;

import com.code_crafters.app.dto.request.UpdateUserRequest;
import com.code_crafters.app.dto.response.UserResponse;
import com.code_crafters.app.entity.User;
import com.code_crafters.app.service.interfaces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getCurrentUserProfile() {
        User currentUser = userService.getCurrentUser();
        UserResponse userResponse = userService.getUserById(currentUser.getId());
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
    
    @PutMapping("/profile")
    public ResponseEntity<?> updateCurrentUserProfile(@RequestBody UpdateUserRequest request) {
        try {
            User currentUser = userService.getCurrentUser();
            UserResponse updatedUser = userService.updateUser(currentUser.getId(), request);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/profile")
    public ResponseEntity<?> deleteCurrentUserProfile() {
        try {
            User currentUser = userService.getCurrentUser();
            userService.deleteUser(currentUser.getId());
            return new ResponseEntity<>("User successfully cancelled", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username) {
        UserResponse userResponse = userService.getUserByUsername(username);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
}