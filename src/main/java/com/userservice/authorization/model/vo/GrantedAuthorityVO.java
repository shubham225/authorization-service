package com.userservice.authorization.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.userservice.authorization.model.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
@JsonSerialize(as = GrantedAuthorityVO.class)
public class GrantedAuthorityVO implements GrantedAuthority {
    private Role role;

    GrantedAuthorityVO(Role role) {
        this.role = role;
    }
    @Override
    @JsonIgnore
    public String getAuthority() {
        return role.getRole();
    }
}
