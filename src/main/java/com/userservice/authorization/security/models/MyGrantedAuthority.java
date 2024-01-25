package com.userservice.authorization.security.models;

import com.userservice.authorization.models.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.userservice.authorization.models.User;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@JsonSerialize(as = MyGrantedAuthority.class)
public class MyGrantedAuthority implements GrantedAuthority {
    private Role role;

    public MyGrantedAuthority() {
        // TODO : Find why, if i do not initialize the role object here spring gives user object null error.
        role = new Role();
    }
    public MyGrantedAuthority(Role role) {
        this.role = role;
    }

    @Override
    @JsonIgnore
    public String getAuthority() {
        return role.getRole();
    }
}
