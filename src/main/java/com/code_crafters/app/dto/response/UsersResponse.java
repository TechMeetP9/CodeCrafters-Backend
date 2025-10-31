package com.code_crafters.app.dto.response;

import java.util.UUID;

import lombok.Data;

@Data
public class UsersResponse {
    private UUID id;
    private String name;
    private String username;
    private String email;
    private String profileImageUrl;
}
