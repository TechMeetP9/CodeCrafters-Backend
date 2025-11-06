package com.code_crafters.app.service.impl;

import com.code_crafters.app.dto.request.RegisterRequest;
import com.code_crafters.app.dto.request.UpdateUserRequest;
import com.code_crafters.app.dto.response.UserResponse;
import com.code_crafters.app.entity.User;
import com.code_crafters.app.mapper.UserMapper;
import com.code_crafters.app.repository.UserRepository;
import com.code_crafters.app.security.UserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.code_crafters.app.service.interfaces.UserService;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    
    private UserRepository userRepository;
    private UserMapper userMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public UserServiceImpl(UserRepository userRepository,
                          UserMapper userMapper,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    @Override
    public UserResponse getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return userMapper.toResponse(user);
    }
    
    @Override
    public UserResponse getUserById(UUID id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return userMapper.toResponse(user);
    }
    
    @Override
    public UserResponse createUser(RegisterRequest request) {
 
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("The username is already taken");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email is already in use");
        }
        
 
        User user = userMapper.toEntity(request);
        

        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }
    
    @Override
    public UserResponse updateUser(UUID id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        

        User currentUser = getCurrentUser();
        if (!currentUser.getId().equals(id)) {
            throw new RuntimeException("You do not have permission to update this user.");
        }
        

        if (request.getEmail() != null && !request.getEmail().equals(user.getEmail())) {
            if (userRepository.existsByEmail(request.getEmail())) {
                throw new RuntimeException("This email is already in use");
            }
        }
        

        userMapper.updateFromRequest(request, user);
        

        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        }
        
        User updatedUser = userRepository.save(user);
        return userMapper.toResponse(updatedUser);
    }
    
    @Override
    public void deleteUser(UUID id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        

        User currentUser = getCurrentUser();
        if (!currentUser.getId().equals(id)) {
            throw new RuntimeException("You do not have permission to delete this user.");
        }
        
        userRepository.delete(user);
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
            .map(user -> new UserDetail(user))
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
    
    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("No authenticated user");
        }
        String username = authentication.getName();
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}