package com.code_crafters.app.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Name cannot be blank.")
    @Size(max = 30, message = "Name cannot exceed 30 characters.")
    private String name;
    
    @NotBlank(message = "Email cannot be blank.")
    @Size(max = 30, message = "Email cannot exceed 30 characters.")
    private String email;

    @NotBlank(message = "Password cannot be blank.")
    @Size(max = 25, message = "Password cannot exceed 25 characters.")
    private String password;
}
