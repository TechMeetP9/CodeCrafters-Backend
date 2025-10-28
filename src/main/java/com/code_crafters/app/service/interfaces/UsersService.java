package com.code_crafters.app.service.interfaces;

import com.code_crafters.app.dto.LoginRequest;
import com.code_crafters.app.dto.RegisterRequest;
import com.code_crafters.app.entity.Users;

import java.util.Optional;

public interface UsersService {

    String register(RegisterRequest request);

    Optional<Users> login(LoginRequest request);
}
