package com.resgateja.dtos;

import com.resgateja.models.Organization;

public record SimpleOrganizationResponse(
        String name,
        String document
) {
    public SimpleOrganizationResponse(Organization organization) {
        this(organization.getName(), organization.getDocument());
    }
}
