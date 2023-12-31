package com.ray.springmail.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestDto {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}
