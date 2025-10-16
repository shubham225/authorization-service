package com.userservice.authorization.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
public class UserDTO {
    private UUID    id;
    @NotBlank
    private String  username;
    @NotBlank
    private String  email;
    private String  mobile;
    private String  address;
    private String  city;
    private String  country;
    private boolean isActive;
    private boolean isAccountLocked;
    @NotEmpty
    private Set<String> roles;
}
