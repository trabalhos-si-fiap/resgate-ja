package com.resgateja.dtos;

import com.resgateja.models.User;
import com.resgateja.models.UserRole;

import java.util.List;

public record UserResponse(
        Long id,
        String name,
        String email,
        UserRole role,
        List<SimpleOrganizationResponse> organizations
) {
    public UserResponse(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getOrganizations().stream().map(SimpleOrganizationResponse::new).toList()
        );
    }
}
