package com.authenticator.user.security.models;

import com.authenticator.user.models.Role;
import com.authenticator.user.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@JsonSerialize(as = GrantedAuthority.class)
public class GrantedAuthority implements org.springframework.security.core.GrantedAuthority {
    private Role role;

    GrantedAuthority(Role role) {
        this.role = role;
    }

    @Override
    @JsonIgnore
    public String getAuthority() {
        return role.getRole();
    }
}
