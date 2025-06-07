package com.resgateja.dtos;

import com.resgateja.interfaces.HasOrganizations;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CreateUser(
        @NotBlank
        String name,
        @NotBlank
        String email,
        String password,
        List<CreateOrganization> organizations
) implements HasOrganizations {}
