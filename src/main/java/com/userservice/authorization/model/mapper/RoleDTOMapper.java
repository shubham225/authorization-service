package com.userservice.authorization.model.mapper;

import com.userservice.authorization.model.dto.RoleDTO;
import com.userservice.authorization.model.entity.Role;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RoleDTOMapper implements Function<Role, RoleDTO> {
    @Override
    public RoleDTO apply(Role role) {
        return RoleDTO.builder()
                .id(role.getId())
                .role(role.getRole())
                .description(role.getDescription())
                .build();
    }
}
