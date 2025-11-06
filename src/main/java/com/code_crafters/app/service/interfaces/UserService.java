package com.code_crafters.app.service.interfaces;

import com.code_crafters.app.dto.request.RegisterRequest;
import com.code_crafters.app.dto.request.UpdateUserRequest;
import com.code_crafters.app.dto.response.UserResponse;
import com.code_crafters.app.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.UUID;

public interface UserService {
    UserResponse getUserByUsername(String username);
    UserResponse getUserById(UUID id);
    UserResponse createUser(RegisterRequest request);
    UserResponse updateUser(UUID id, UpdateUserRequest request);
    void deleteUser(UUID id);
    UserDetails loadUserByUsername(String username);
    User getCurrentUser(); // Devuelve Entity para uso interno
}