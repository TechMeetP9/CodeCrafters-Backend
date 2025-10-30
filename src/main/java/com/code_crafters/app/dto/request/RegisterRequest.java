package com.code_crafters.app.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "Name cannot be blank.")
    @Size(max = 30, message = "Name cannot exceed 50 characters.")
    private String name;

    @NotBlank(message = "Username cannot be blank.")
    @Size(max = 30, message = "Username cannot exceed 10 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9._-]{3,30}$", message = "Username can only contain letters, numbers, dots, underscores and hyphens.")
    private String username;

    @NotBlank(message = "Email cannot be blank.")
    @Email(message = "Email should be valid.")
    @Size(max = 50, message = "Email cannot exceed 100 characters.")
    private String email;

    @NotBlank(message = "Password cannot be blank.")
    @Size(min = 6, max = 25, message = "Password must be between 6 and 10 characters.")
    private String password;
}
