package com.resgateja.infra.security;

import com.resgateja.models.UserRole;
import org.springframework.security.core.GrantedAuthority;

public class RoleGrantedAuthority implements GrantedAuthority {
    private final String authority;

    public RoleGrantedAuthority(UserRole role) {
        this.authority = "ROLE_" + role.name();
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}