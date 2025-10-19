package com.userservice.authorization.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class RoleDTO {
    private UUID id;
    @NotBlank
    private String role;
    private String description;
}
