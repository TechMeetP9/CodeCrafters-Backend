package com.code_crafters.app.service.interfaces;

import com.code_crafters.app.dto.request.LoginRequest;
import com.code_crafters.app.dto.request.RegisterRequest;
import com.code_crafters.app.entity.Users;

import java.util.Optional;

public interface UsersService {

    Users register(RegisterRequest request);

    Optional<Users> login(LoginRequest request);

    Optional<Users> findById(Long id); 
}
