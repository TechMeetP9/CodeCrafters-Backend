package com.code_crafters.app.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequest {

    @Size(max = 30, message = "Name cannot exceed 30 characters.")
    private String name;

    @Email(message = "Email should be valid.")
    @Size(max = 50, message = "Email cannot exceed 50 characters.")
    private String email;

    @Size(max = 200, message = "Profile image URL cannot exceed 200 characters.")
    private String profileImageUrl;

    @Size(min = 6, max = 25, message = "Password must be between 6 and 25 characters.")
    private String password;
}
