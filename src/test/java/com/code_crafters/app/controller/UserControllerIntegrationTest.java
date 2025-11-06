package com.code_crafters.app.controller;


import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.http.MediaType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.code_crafters.app.dto.response.UserResponse;
import com.code_crafters.app.service.interfaces.UserService;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@ContextConfiguration (classes = {UserController.class})
@AutoConfigureMockMvc (addFilters = false)
public class UserControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    void testGetUserByUsername() throws Exception{
        String username = "jensen_sn";
        UserResponse mockResponse = new UserResponse(
            UUID.randomUUID(),
            "Jensen",
            "jensen_sn",
            "jensen.a@example.com", "https://example.com/profile1.png", LocalDateTime.now());

        when(userService.getUserByUsername(username)).thenReturn(mockResponse);


        mockMvc.perform(get("/api/users/username/{username}", username)
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.username").value("jensen_sn"))
               .andExpect(jsonPath("$.email").value("jensen.a@example.com"))
               .andExpect(jsonPath("$.name").value("Jensen"));
    }

    
    
}
