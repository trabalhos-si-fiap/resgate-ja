package com.resgateja.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthData(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password
) {
}
