package com.code_crafters.app.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.code_crafters.app.entity.User;
import com.code_crafters.app.repository.UserRepository;
import com.code_crafters.app.service.interfaces.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @BeforeEach
    void setup(){
       // User user1 = new User(1L, "john", "johnny88", "john@example.com", "password", "https://example.com/profile1.png", LocalDateTime.now(), LocalDateTime.now());
    }
}
