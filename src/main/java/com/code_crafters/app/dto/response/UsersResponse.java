package com.code_crafters.app.dto.response;

import lombok.Data;

@Data
public class UsersResponse {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String profileImageUrl;
}
