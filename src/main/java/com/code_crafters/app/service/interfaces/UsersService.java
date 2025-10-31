package com.code_crafters.app.service.interfaces;

import com.code_crafters.app.dto.request.LoginRequest;
import com.code_crafters.app.dto.request.RegisterRequest;
import com.code_crafters.app.dto.request.UpdateUserRequest;
import com.code_crafters.app.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UsersService {
    User register(RegisterRequest request);
    Optional<User> login(LoginRequest request);
    Optional<User> findById(UUID id);
    User updateUser(UUID id, UpdateUserRequest request);
    void deleteUser(UUID id);
}
