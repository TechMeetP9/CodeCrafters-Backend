package com.code_crafters.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.code_crafters.app.dto.request.LoginRequest;
import com.code_crafters.app.dto.response.JwtResponse;
import com.code_crafters.app.entity.User;
import com.code_crafters.app.mapper.UsersMapper;
import com.code_crafters.app.service.interfaces.UsersService;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
    @Mock
    private UsersService usersService;

    @Mock
    private UsersMapper usersMapper;


    @InjectMocks
    private AuthController authController;

    private LoginRequest loginRequest;
    private User mockUser;


    @BeforeEach
    void setUp(){
        loginRequest = new LoginRequest();
        loginRequest.setEmail("ana@example.com");
        loginRequest.setPassword("password");

        mockUser = new User();
        mockUser.setId(UUID.randomUUID());
        mockUser.setUsername("ana");


    

    }

    @Test
    void authUser_Success(){
        JwtResponse mockJwt = new JwtResponse("fjghlsjdhfs", "ana", "ana@example.com");
        
        when(usersService.login(loginRequest)).thenReturn(Optional.of(mockUser));
        when(usersMapper.toJwtResponse(mockUser)).thenReturn(mockJwt);

        ResponseEntity<?> response = authController.authenticateUser(loginRequest);

        assertEquals(200, response.getStatusCodeValue());
    assertNotNull(response.getBody(), "Response body should not be null");

    Object body = response.getBody();
    assertTrue(body instanceof JwtResponse, "Body should be a JwtResponse");

    JwtResponse jwtResponse = (JwtResponse) body;
    assertNotNull(jwtResponse.getToken(), "Token should not be null");
    assertEquals("fjghlsjdhfs", jwtResponse.getToken());

    verify(usersService, times(1)).login(loginRequest);
    verify(usersMapper, times(1)).toJwtResponse(mockUser);
    }

    
}