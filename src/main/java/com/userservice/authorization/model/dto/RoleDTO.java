package com.userservice.authorization.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class RoleDTO {
    private UUID id;
    private String role;
    private String description;
}
