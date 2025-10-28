package com.code_crafters.app.service.impl;

import com.code_crafters.app.dto.request.LoginRequest;
import com.code_crafters.app.dto.request.RegisterRequest;
import com.code_crafters.app.entity.Users;
import com.code_crafters.app.repository.UsersRepository;
import com.code_crafters.app.service.interfaces.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.code_crafters.app.mapper.UsersMapper;

import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersMapper usersMapper; //

    @Override
    public Users register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email is already in use");
        }

        Users user = usersMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public Optional<Users> login(LoginRequest request) {
        Optional<Users> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isPresent()) {
            Users user = userOpt.get();
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Users> findById(Long id) {
        return userRepository.findById(id);
    }
}
