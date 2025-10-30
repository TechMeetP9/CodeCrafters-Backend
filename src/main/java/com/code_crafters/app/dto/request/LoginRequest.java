package com.code_crafters.app.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Password cannot be blank.")
    @Size(max = 25, message = "Password cannot exceed 10 characters.")
    private String password;

    @NotBlank(message = "Email cannot be blank.")
    @Size(max = 30, message = "Email cannot exceed 100 characters.")
    private String email;
}
