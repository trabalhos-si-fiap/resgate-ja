package com.resgateja.dtos;

import com.resgateja.interfaces.HasOrganizations;

import java.util.List;

public record UpdateUser(
        String name,
        String password,
        List<CreateOrganization> organizations
) implements HasOrganizations {
}
