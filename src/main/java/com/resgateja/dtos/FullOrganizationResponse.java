package com.resgateja.dtos;

import com.resgateja.models.Organization;

import java.time.LocalDateTime;

public record FullOrganizationResponse(
        Long id,
        String name,
        String document,
        boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public FullOrganizationResponse(Organization organization) {
        this(
                organization.getId(),
                organization.getName(),
                organization.getDocument(),
                organization.isActive(),
                organization.getCreatedAt(),
                organization.getUpdatedAt()
        );
    }
}
