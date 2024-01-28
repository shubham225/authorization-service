package com.userservice.authorization.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserResponseDto {
    private String username;
    @JsonProperty( value = "is_active")
    private Boolean isActive;
//    private Set<String> roles;

    public UserResponseDto() {
//        roles = new HashSet<>();
    }
}
